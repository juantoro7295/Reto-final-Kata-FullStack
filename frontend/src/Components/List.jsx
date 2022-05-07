import React, { useContext, useEffect } from "react";
import { HOST_API } from "../App";
import { Store } from "../App";

const List = ({ id }) => {
  const {
    dispatch,
    state: { todo },
  } = useContext(Store);
  const currentList = todo.list;

  // filtro de listas por medio del id de TodoList
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

  const onDelete = (id_todo) => {
    fetch(HOST_API + "/todo/delete/" + id_todo, {
      method: "DELETE",
    }).then((list) => {
      dispatch({ type: "delete-item", id_todo });
    });
  };

  const onEdit = (todo) => {
    dispatch({ type: "edit-item", item: todo });
  };

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
   *  funcion para desactivar el boton editar en cuando este listo el ToDo
   */
  const editOff = (param) => (param ? true : false);

  const decorationDone = {
    textDecoration: "line-through",
  };

  return (
    <div className="m-2">
      <table className="table table-striped table-hover">
        <thead>
          <tr>
            <td>ID</td>
            <td>Tarea</td>
            <td>¿Completado?</td>
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
                      className="btn btn-dark btn-sm"
                      onClick={() => onDelete(todo.id_todo)}
                    >
                      Eliminar
                    </button>
                  </div>
                  <div className="m-1">
                    <button
                      disabled={editOff(todo.completed)}
                      onClick={() => onEdit(todo)}
                      className="por aca estoy loco btn btn-primary btn-sm"
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