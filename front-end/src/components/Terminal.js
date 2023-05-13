import { useState } from 'react';

function Terminal() {
    const [output, setOutput] = useState('Await user to submit code');

    function handleInput(event) {
      // Handle user input here
    }

  return (
    <div className="bg-gray-900 text-white font-mono p-4 w-full"  style={{height:"70vh"}}>
      <div className="flex items-center">
        <div className="w-3 h-3 rounded-full bg-red-500 mr-2"></div>
        <div className="w-3 h-3 rounded-full bg-yellow-500 mr-2"></div>
        <div className="w-3 h-3 rounded-full bg-green-500 mr-2"></div>
      </div>
      <div className="h-64 overflow-y-scroll mb-4 w-full" style={{height:"100%"}}>
        {/* Render the output here */}
        <br></br>
        {output}
      </div>
      <div className="flex">
        <span className="text-white mr-2">$</span>
        <input
          type="text"
          className="flex-1 bg-transparent outline-none"
            // onKeyUp={handleInput}
        />
      </div>
    </div>
  );
}
export default Terminal;
