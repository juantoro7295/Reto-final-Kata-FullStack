import React, { Fragment, useContext, useEffect } from "react";
import NewTodoForm from "./NewTodoForm";
import { HOST_API, Store } from "../App";
import List from "./List";

const ListTodos = () => {
  const {
    dispatch,
    state: { todoList },
  } = useContext(Store);
  const lista = todoList.list;
  //TODO pendiente la peticion de bd
  useEffect(() => {
    fetch(HOST_API + "/todolist/list")
      .then((res) => res.json())
      .then((list) => {
        dispatch({ type: "update-listTodos", list });
      });
  }, [dispatch]);

  //TODO pendiente la peticion de bd
  const onDelete = (id_groupList) => {
    fetch(HOST_API + "/todolist/get"+ id_groupList, {
      method: "DELETE",
    }).then((list) => {
      dispatch({ type: "delete-listTodos", id_groupList });
    });

    const deleteDiv = document.getElementById("rowList");
    deleteDiv.removeChild(document.getElementById(id_groupList));
  };

  return lista.map((todoList) => {
    return (
      <Fragment key={todoList.id_groupList}>
        <div className="col-md-6 mb-2" id={todoList.id_groupList}>
          <div className="card custom-card">
            <div className="m-2 rounded bkGrey" key={todoList.id_groupList}>
              <div className="m-2">
                <h2>{todoList.name}</h2>
                <button
                  className="btn btn-outline-danger"
                  onClick={() => onDelete(todoList.id_groupList)}
                >
                  Borrar Lista
                </button>
              </div>
              <NewTodoForm id={todoList} />
              <List id={todoList} />
            </div>
          </div>
        </div>
      </Fragment>
    );
  });
};

export default ListTodos;