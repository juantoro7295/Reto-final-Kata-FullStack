import React, { useContext, useEffect } from "react";
import { HOST_API } from "../App";
import { Store } from "../App";

const List = ({ id }) => {
  const {
    dispatch,
    state: { todo },
  } = useContext(Store);
  const currentList = todo.list;


  const show = currentList.filter(
    (event) => event.id_todolist === id.id_todolist
  );
  console.log(`lista: ${show}`)

  useEffect(() => {
    fetch(HOST_API + "/todo/list")
      .then((response) => response.json())
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
      id_todolist: todo.id_todolist,
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

  const editOff = (param) => (param ? true : false);

  const decorationDone = {
    textDecoration: "line-through",
  };

  return (
    <div className="m-2">
      <table className="table table-striped table-hover">
        <thead>
          <tr>
            <td>Codigo</td>
            <td>Tarea</td>
            <td>¿ Completado ?</td>

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
                {console.log(todo.id_todo)}
                <td>{todo.name}</td>
                {console.log(todo.name)}
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
                      id="buttonDelete"
                      onClick={() => onDelete(todo.id_todo)}
                    >
                      Eliminar
                    </button>
                  </div>
                </td>
                <td>
                  <div className="m-1">
                    <button
                      disabled={editOff(todo.completed)}
                      onClick={() => onEdit(todo)}
                      className="btn btn-success"
                      id="buttonEdit"
                    >
                      Editar
                    </button>
                  </div>
                </td>
              </tr>
            )



          })}
        </tbody>
      </table>
    </div>
  );
};

export default List;