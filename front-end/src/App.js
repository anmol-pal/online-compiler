import './App.css';
import React, { useState, useRef } from 'react';
import Header from './components/Header';
import Footer from './components/Footer';
import Editor from '@monaco-editor/react';
import SelectionComponent from './components/SelectionComponent';
import Terminal  from './components/Terminal';
import ExecuteButton from './components/ExecuteButton';
import Home from './components/Home';
import './index.css';
import SideNav from './components/SideNav';
import fileData from './components/file.json'
import languageOptions from './components/DeploymentLanguageOptions.json' 
const files = {
  "script.py": {
    name : "script.py" ,
    language : "python" ,
    value : "import java.util.*;\npublic class Main { public static void main(String[] args)\n\t{\n\t\tSystem.out.println(\"Hello,  world!\");\n\t}\n}"
  },
  "index.html": {
    name : "index.html" ,
    language : "html" ,
    value : "<h1> </h1>"
  }
}

function App() {
  const [user, setUser] = useState('')
  const [code, setCode] = useState('')
  const [extension, setExtension] = useState(languageOptions[0].extension)
  const [language, setLanguage] = useState(languageOptions[0].value);
  const [filename, setfilename] = useState("script.py");
  const [fetchResponse, setFetchResponse] = useState("null")
  const [fetchUrl, setFetchUrl] = useState(languageOptions[0].fetchUrl)
  const [port, setPort] = useState(languageOptions[0].port)
  const file = files[filename]
  const [submitted, setSubmitted] = useState(false);
  const [userFiles, setUserFiles] = useState(fileData[0])
  // const [fileData, setfileData] = useState(fileData[0])
  const BUCKET="35.243.153.225";
  async function handleEditorDidMount(editor, monaco){
    const URL = `http://${BUCKET}:8086/users/${user}`


    try{
      const response = await fetch (URL).then(response => response.json());
      // file.value = response;
      console.log("11111111111111111111 "+response);
      fileData = response;
    }
    catch(err){
      console.log("Not ok")
    }



    // setUser('apal6')
    console.log("Mount")
    console.log(user)
    const populateUserFiles = fileData.filter(rec => rec.user_id === user )
    // const populateUserFiles = filedata.filter(rec => {
    //   if(rec.user_id===user){
    //     console.log("Here  "+JSON.stringify(rec))
    //     return {rec}
    //   }
    // })
    setUserFiles(populateUserFiles)
    // console.log("Look at obj   "+JSON.stringify(populateUserFiles[0]))
    populateUserFiles.map(x=> console.log(" bbbb "+ x.user_id))
    editorRef.current = editor;
  }

  const editorRef = useRef(null)

  const getEditorValue= async (event)=>{
    event.preventDefault();
    const temp = editorRef.current.getValue();
    setCode(temp)
    const postData={
      "userId": user,
      "language": language,
      "extension": extension,
      "code" : temp,
    }
    try{
      const URL = `http://${fetchUrl}:${port}/${language}/execute`
      console.log(URL)
      const response = await fetch (URL, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(postData)
      });
      const responseData = await response.json();
        console.log(responseData.port)
        const ttydPort= Number(responseData.port);
        console.log("ttydport "+ttydPort)
        const COMPILER_SERVICE_URL = `http://${fetchUrl}`
        const iifr = document.createElement('iframe');
        iifr.src = `${COMPILER_SERVICE_URL}:${ttydPort}`
        iifr.id = 'ttyd'
        iifr.style.cssText = 'height:70vh ;width:100%';
        const ttyDiv=document.getElementById('ttydDiv');
        setTimeout(()=>{
          setFetchResponse(ttydPort)
        })
        // if(ttydPort===8081){
          setTimeout(()=>{
            ttyDiv.innerHTML=""
            ttyDiv.appendChild(iifr);
            ttyDiv.className = 'col-span-2 bg-white-200';
            ttyDiv.style.cssText = 'height:95% ;width:100% ';

          },2000)
        // }
    }
    catch(err){
      console.log("Not ok")
    }
  }

  const handleInputChange=(event)=>{
    console.log("Handler called , look at the value"+ event.target.value);
    setUser(event.target.value);
  }
  const handleSubmit = (event) => {
    event.preventDefault();
    setSubmitted(true);
    setUser(event.target.elements.user.value);
  }
  function handleLanguageChange(event){
    const lang = event.target.value;
    const language=languageOptions.find((element)=>element.value===lang);
    setLanguage(lang);
    setCode(language.initSnippet);
    setFetchUrl(language.fetchUrl);
    setPort(language.port);
    setExtension(language.extension);
  }

  //Changes - Akash
  async function handleFileClick(fileName) {
    // console.log(`File with ID ${fileName} was clicked`);
    try{
      const URL = `http://${BUCKET}:8086/users/file/${fileName}`
      console.log(URL)
      const response = await fetch (URL).then(response => response.text());
      // file.value = response;
      const lang = fileName.split(".")[1];
      const language=languageOptions.find((element)=>element.value===lang);
      setLanguage(lang);
      setCode(response);
      setFetchUrl(language.fetchUrl);
      setPort(language.port);
      setExtension(language.extension);
    }
    catch(err){
      console.log("Not ok")
    }
  }

  return (
    <div className="App" class="relative h-full w-full">
          <Header user={user} />
          {! submitted && <Home inputValue={user} onInputChange={handleInputChange} onSubmit={handleSubmit} />}
          {
            submitted && <>
              <SelectionComponent options = { languageOptions }  onChange = { handleLanguageChange } />
              <form onSubmit={getEditorValue}>
              <div class="grid grid-cols-6 px-2 mt-3 ">
                  <div class="col-span-1">
                    <SideNav userFiles = { fileData } onFileClick={handleFileClick}/>
                  </div>
                  <div class="col-span-3 bg-white-200 px-5">
                      <Editor 
                        height="70vh"
                        theme="vs-dark"
                        path={file.name}
                        defaultLanguage={file.language}
                        defaultValue={file.value}
                        onMount={handleEditorDidMount}
                        value={code}
                      />
                      <ExecuteButton />
                  </div>
                    <div class="col-span-2 bg-white-200" id="ttydDiv">
                      <Terminal />
                    <div/>
                </div>
              </div>
            </form>            
            </>
          }
      <Footer/>
    </div>
  );
}


export default App;