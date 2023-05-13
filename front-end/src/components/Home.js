import React from 'react';

function Home({ inputValue, onInputChange,onSubmit }) {
  return (
    <section class="h-screen mr-20 ml-20">
    <div class="h-4/5 ">
      <div class="g-6 flex h-full flex-wrap items-center justify-center lg:justify-between">
        <div class="shrink-1 grow-0 basis-auto md:mb-0 md:w-9/12 md:shrink-0 lg:w-6/12 xl:w-6/12 mt-2">
          <img 
            src="https://cdn-icons-png.flaticon.com/512/3242/3242120.png"
            class="max-w-xs" alt="Sample image" />
        </div>
      <div class="mb-6 md:mb-0 md:w-8/12 lg:w-5/12 xl:w-5/12">
        <form action={`/runcode/${inputValue}`} onSubmit={onSubmit} method="GET" >
          <div
            class="my-4 flex items-center before:mt-0.5 before:flex-1 before:border-t before:border-neutral-300 after:mt-0.5 after:flex-1 after:border-t after:border-neutral-300">
            <p class="mx-4 mb-0 text-center font-semibold dark:text-dark">
              Code without any hassle
            </p>
          </div>

          <div class="relative mb-4" data-te-input-wrapper-init>
            <div class="relative z-0">
                <input type="text" id="user" class="block py-2.5 px-0 w-full text-sm text-gray-900 bg-transparent border-0 border-b-2 border-gray-300 appearance-none dark:text-dark dark:border-gray-600 dark:focus:border-blue-500 focus:outline-none focus:ring-0 focus:border-blue-600 peer" placeholder=" " value={inputValue} onChange={onInputChange}/>
                <label for="user" class="absolute text-sm text-gray-500  duration-300 transform -translate-y-6 scale-75 top-3 -z-10 origin-[0] peer-focus:left-0 peer-focus:text-blue-600 peer-focus:dark:text-blue-500 peer-placeholder-shown:scale-100 peer-placeholder-shown:translate-y-0 peer-focus:scale-75 peer-focus:-translate-y-6">User Name</label>
            </div>
          </div>

          <div class="mb-4 flex items-center justify-between">
          </div>
          <div class="text-center lg:text-left">
            <button type="submit"
              class="inline-block rounded bg-primary px-7 pb-2.5 pt-3 text-sm font-medium uppercase leading-normal text-black shadow-[0_4px_9px_-4px_#3b71ca] transition duration-150 ease-in-out hover:bg-primary-600 hover:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:bg-primary-600 focus:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)] focus:outline-none focus:ring-0 active:bg-primary-700 active:shadow-[0_8px_9px_-4px_rgba(59,113,202,0.3),0_4px_18px_0_rgba(59,113,202,0.2)]"
              data-te-ripple-init data-te-ripple-color="dark">
              SUBMIT
            </button>
          </div>
        </form>
      </div>
    </div>
    </div>
  </section>
  )
}
export default Home;