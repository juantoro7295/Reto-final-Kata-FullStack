import React,{createContext,Fragment} from 'react'
import StoreProvider from "./reducer/StoreProvider";
import FormListTodo from "./Components/FormListTodo";
import ListTodos from "./Components/ListTodos";

export const HOST_API = "http://localhost:8080/api";
export const initialState = {
  todo: { list: [], item: {} },
  todoList: { list: [], item: {} },
};

export const Store = createContext(initialState);





function App() {
  return (
    <Fragment>
      <div className="container">
        <StoreProvider>
          <FormListTodo />
          <div id="createAlert"></div>
          <div className="row" id="rowList">
            <ListTodos />
          </div>
        </StoreProvider>
      </div>
    </Fragment>
  );
}

export default App;
