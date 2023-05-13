import React from 'react';
// import { CheckIcon } from '@heroicons/react/outline'

function ExecuteButton(props){
    return(
        <button className=" mt-2 mb-2 px-4 py-2 bg-green-500 text-white rounded-lg flex items-center">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
                <path strokeLinecap="round" strokeLinejoin="round" d="M5.25 5.653c0-.856.917-1.398 1.667-.986l11.54 6.348a1.125 1.125 0 010 1.971l-11.54 6.347a1.125 1.125 0 01-1.667-.985V5.653z" />
            </svg>
        Run Code
        </button>

    )
}
export default ExecuteButton;


