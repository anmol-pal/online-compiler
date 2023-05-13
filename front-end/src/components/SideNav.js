import { useState } from 'react';

function SideNav(props) {
    const {userFiles, onFileClick} = props
    console.log("all the files \n"+ userFiles)
    userFiles.map(x=> console.log( x.file_blob_id ))

  return (
    <div>
        <link rel="stylesheet" href="https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css" />
        <div class="min-h-screen flex flex-row bg-gray-100">
            <div class="flex flex-col w-56 bg-white overflow-hidden">
                <div class="flex items-center justify-center h-20 shadow-md">
                    <h1 class="text-3l uppercase text-indigo-500">User Files</h1>
                </div>
                <ul class="flex flex-col py-4">
                    {
                        userFiles.map((record)=>(
                         <li key={record.file_blob_id}>
                            <a href="#" 
                                onClick={() => onFileClick(record.file_blob_id)}
                                class="flex flex-row items-center h-12 transform hover:translate-x-2 transition-transform ease-in duration-200 text-gray-500 hover:text-gray-800">
                                <span class="inline-flex items-center justify-center h-12 w-12 text-sm text-gray-400"><i class="bx bx-log-out"></i></span>
                                <span class="text-xs text-bold">{record.file_blob_id}</span>
                            </a>
                          </li>                      
                    ))}
                </ul>
            </div>
        </div>
    </div>
  )
}
export default SideNav;



