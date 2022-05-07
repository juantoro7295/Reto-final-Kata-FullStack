import React, { useRef, useContext, useState } from "react";
import { HOST_API } from "../App";
import { Store } from "../App";

const NewTodoForm = ({ id }) => {
  const formRef = useRef(null);
  const {
    dispatch,
    state: { todo },
  } = useContext(Store);
  const item = todo.item;
  const [state, setState] = useState(item);

  const onAdd = (event) => {
    event.preventDefault();

    const request = {
      name: state.name,
      id_todo: null,
      completed: false,
      id_groupList: id.id_groupList,
    };

    fetch(HOST_API + "/todo/save", {
      method: "POST",
      body: JSON.stringify(request),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => response.json())
      .then((todo) => {
        dispatch({ type: "add-item", item: todo });
        setState({ name: "" });
        formRef.current.reset();
      });
  };

  const onEdit = (event) => {
    event.preventDefault();

    const request = {
      name: state.name,
      id_todo: item.id_todo,
      isCompleted: item.isCompleted,
      id_groupList: id.id_groupList,
    };
    fetch(HOST_API + "/todo/save", {
      method: "PUT",
      body: JSON.stringify(request),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => response.json())
      .then((todo) => {
        dispatch({ type: "update-item", item: todo });
        setState({ name: "" });
        formRef.current.reset();
      });
  };


  const wihtOutTodo = (name) =>
    name === undefined || name.lenght === 0 ? true : false;

  return (
    <form ref={formRef}>
      <input
        type="text"
        name="name"
        placeholder="¿Qué vas hacer?"
        onChange={(event) => {
          setState({ ...state, name: event.target.value });
        }}
        defaultValue={item.name}
      ></input>
      {item.id_todo && (
        <button disabled={wihtOutTodo(state.name)} onClick={onEdit}>
          Actualizar
        </button>
      )}
      {!item.id_todo && (
        <button disabled={wihtOutTodo(state.name)} onClick={onAdd}>
          Crear
        </button>
      )}
    </form>
  );
};

export default NewTodoForm;