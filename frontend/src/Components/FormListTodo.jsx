import React, { useContext, useRef, useState } from "react";
import { HOST_API, Store } from "../App";

/**
 * Componente FormList
 *
 * @author Juan Pablo Toro Hurtado.
 * @version 1.0.0
 * @since 1.0.0
 */

const FormListTodo = () => {

  const formRef = useRef(null);
  const {
    dispatch,
    state: { todoList },
  } = useContext(Store);
  const item = todoList.item;
  const [state, setState] = useState(item);

  /**
   * Funcion que nos permite agregar una lista de ToDo
   * @param event 
   */
  const onAdd = (event) => {
    event.preventDefault();
    const request = {
      name: state.name,
      id_groupList: null,
    };
      
    if (state.name.length < 3) {
      let createAlert = document.getElementById("createAlert");
      createAlert.innerHTML = `<div class="alert alert-warning">
                <h3>Ingresa un nombre a la lista no vacio</h3></div>`;
      setTimeout(() => {
        createAlert.innerHTML = "";
      }, 3000);
    } else {
      fetch(HOST_API + "/todolist/save", {
        method: "POST",
        body: JSON.stringify(request),
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((res) => res.json())
        .then((todoList) => {
          dispatch({ type: "add-listTodo", item: todoList });
          setState({ name: "" });
          formRef.current.reset();
        });
    }
  };
  return (
    <div className="row">
      <div className="col-2"></div>
      <div className="col-8">
        <form ref={formRef}>
        <h1 id="listaTodos">Lista de ToDos</h1>
          <div id="contenedorList" className="input-group mb-2">
            <input
              id= "inputList"
              type="text"
              name="name"
              placeholder="Nueva Lista de ToDo"
              className="form-control"
              onChange={(event) => {
                setState({ ...state, name: event.target.value });
              }}
            ></input>
            <button onClick={onAdd} className="btn btn-primary">
              Agregar Lista
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};
export default FormListTodo;