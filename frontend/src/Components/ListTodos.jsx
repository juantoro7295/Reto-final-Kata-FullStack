import React, { Fragment, useContext, useEffect } from "react";
import NewTodoForm from "./NewTodoForm";
import { HOST_API, Store } from "../App";
import List from "./List";

/**
 * Componente ListTodos
 *
 * @author Juan Pablo Toro Hurtado.
 * @version 1.0.0
 * @since 1.0.0
 */


const ListTodos = () => {
  const {
    dispatch,
    state: { todoList },
  } = useContext(Store);
  const lista = todoList.list;

  useEffect(() => {
    fetch(HOST_API + "/todolist/list")
      .then((res) => res.json())
      .then((list) => {
        dispatch({ type: "update-groupList", list });
      });
  }, [dispatch]);

  /**
   * Funcion que nos permite eliminar una Lista de ToDos
   * @param  id_groupList 
   */
  const onDelete = (id_groupList) => {
    fetch(HOST_API + "/todolist/delete/"+id_groupList , {
      method: "DELETE",
    }).then((list) => {
      dispatch({ type: "delete-listTodos", id_groupList });
    });

    /**
     * Nos permite eliminar una columna de la lista de ToDo almacenada
     */
    const deleteDiv = document.getElementById("rowList");
    deleteDiv.removeChild(document.getElementById(id_groupList));
  };

  return lista.map((todosList) => {
    return (
      <Fragment key={todosList.id_groupList}>
        <div className="col-md-6 mb-2" id={todosList.id_groupList}>
          <div className="card custom-card">
            <div className="m-2 rounded bkGrey" key={todosList.id_groupList}>
              <div className="m-2">
                <button
                  class="btn btn-danger"
                  onClick={() => onDelete(todosList.id_groupList)}
                >
                  Borrar Lista
                </button>
                <h2>{todosList.name}</h2>
              </div>
              <NewTodoForm id={todosList} />
              <List id={todosList} />
            </div>
          </div>
        </div>
      </Fragment>
    );
  });
};

export default ListTodos;