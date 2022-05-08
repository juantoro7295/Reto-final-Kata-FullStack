import React, { useContext, useEffect } from "react";
import { HOST_API } from "../App";
import { Store } from "../App";

/**
 * Componente List
 *
 * @author Juan Pablo Toro Hurtado.
 * @version 1.0.0
 * @since 1.0.0
 */

const List = ({ id }) => {
  const {
    dispatch,
    state: { todo },
  } = useContext(Store);
  const currentList = todo.list;

  /**
   * Nos permite filtrar listas a traves de el id
   */
  const show = currentList.filter(
    (event) => event.id_groupList === id.id_groupList
  );

  useEffect(() => {
    fetch(HOST_API + "/todo/list")
      .then((res) => res.json())
      .then((list) => {
        dispatch({ type: "update-list", list });
      });
  }, [dispatch]);

  /**
   * Funcion que nos permite eliminar un ToDo a traves del id
   * @param  id_todo 
   */

  const onDelete = (id_todo) => {
    fetch(HOST_API + "/todo/delete/" + id_todo, {
      method: "DELETE",
    }).then((list) => {
      dispatch({ type: "delete-item", id_todo });
    });
  };

  /**
   * Funcion que nos permite editar un ToDo
   * @param  todo 
   */

  const onEdit = (todo) => {
    dispatch({ type: "edit-item", item: todo });
  };

  
  /**
   * Funcion que nos permite modicar el estado en que se encuentra un ToDo
   * @param  event 
   * @param  todo 
   */
  const onChange = (event, todo) => {
    const request = {
      name: todo.name,
      id_todo: todo.id_todo,
      completed: event.target.checked,
      id_groupList: todo.id_groupList,
    };

    fetch(HOST_API + "/todo/update", {
      method: "PUT",
      body: JSON.stringify(request),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => response.json())
      .then((todo) => {
        dispatch({ type: "update-item", item: todo });
      });
  };
 
  /**
   * Funcion que nos permite activar o desactivar el boton editar
   * @param  param 
   * @returns booleano 
   */
  const editOff = (param) => (param ? true : false);

  /**
   * Efecto  visual para dar por completada una tarea (Raya El ToDo) 
   */
  const decorationDone = {
    textDecoration: "line-through",
  };

  return (
    <div id="cuadro" className="m-2">
      <table className="table table-striped table-hover">
        <thead>
          <tr>
            <td>codigo</td>
            <td>Tarea</td>
            <td>¿Terminado?</td>
          </tr>
        </thead>
        <tbody>
          {show.map((todo) => {
            return (
              <tr
                key={todo.id_todo}
                style={todo.completed ? decorationDone : {}}
              >
                <th scope="row">{todo.id_todo}</th>
                <td>{todo.name}</td>
                <td>
                  <input
                    className="form-check-input"
                    type="checkbox"
                    defaultChecked={todo.completed}
                    onChange={(event) => onChange(event, todo)}
                  ></input>
                </td>
                <td>
                  <div className="m-1">
                    {" "}
                    <button
                      className="btn btn-danger"
                      onClick={() => onDelete(todo.id_todo)}
                    >
                      Eliminar
                    </button>
                  </div>
                  <div className="m-1">
                    <button
                      disabled={editOff(todo.completed)}
                      onClick={() => onEdit(todo)}
                      className="btn btn-warning"
                    >
                      Editar
                    </button>
                  </div>
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
};

export default List;