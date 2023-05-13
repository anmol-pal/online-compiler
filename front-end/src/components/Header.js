import React, { useContext } from 'react';
function Header({user}) {
  console.log("WHAT USER ????"+ user);
  return (
    <>
      <div class="mx-auto box-content flex h-16  items-center justify-between py-1">
        <div class="flex items-center space-x-2 p-4">
          <nav id="navr" class="absolute top-0 mt-16 hidden w-full translate-y-2 items-center bg-white pb-3 text-sm font-medium text-gray-500 md:relative md:mt-0 md:flex md:w-auto md:translate-y-0 md:space-x-2 md:bg-transparent md:pb-0">
            <a href="/" class="block w-full bg-white px-5 py-3 transition hover:bg-gray-100 md:inline md:w-auto md:rounded md:px-3 md:py-2">
                <h1 class="text-3l uppercase text-indigo-500 w-48">Cloud Compiler</h1>
              </a>
            <a href="/" class="block w-full bg-white px-5 py-3 transition hover:bg-gray-100 md:inline md:w-auto md:rounded md:px-3 md:py-2">Home</a>
            <a href="#_" class="block w-full bg-white px-5 py-3 transition hover:bg-gray-100 md:inline md:w-auto md:rounded md:px-3 md:py-2">Features</a>
            <a href="#_" class="block w-full bg-white px-5 py-3 transition hover:bg-gray-100 md:inline md:w-auto md:rounded md:px-3 md:py-2">About</a>
            <a href="#_" class="block w-full bg-white px-5 py-3 transition hover:bg-gray-100 md:inline md:w-auto md:rounded md:px-3 md:py-2">Blog</a>
          </nav>
        </div>
        <p class="capitalize hover:uppercase block w-full bg-white px-5 py-3 transition hover:bg-gray-100 md:inline  md:rounded md:px-3 md:py-2 font-semibold text-gray-900 ml-8">
          Welcome {user}
        </p>
        
      </div>
      <div>
      
      </div>    
    </>
    );
}
export default Header;