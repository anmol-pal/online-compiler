import React, { useState } from 'react';

function SelectionComponent(props) {
  const { value, options, onChange } = props
  return (
      <select value={value} onChange={onChange} 
      style={{display:"inline-block",position:"absolute",top:"20px",right:"10px"}}
        class=" mt-3 mb-3 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-1/6 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 ml-8 mb-5" >
        {options.map((option) => (
          <option key={option.value} value={option.value}>
            {option.label}
          </option>
        ))}
      </select>
  );
}

export default SelectionComponent;
