import React, { useRef, useContext, useState } from "react";
import { HOST_API } from "../App";
import { Store } from "../App";

/**
 * Componente NewTodoForm
 *
 * @author Juan Pablo Toro Hurtado.
 * @version 1.0.0
 * @since 1.0.0
 */


const NewTodoForm = ({ id }) => {
    const formRef = useRef(null);
    const {
        dispatch,
        state: { todo },
    } = useContext(Store);
    const item = todo.item;
    const [state, setState] = useState(item);

    /**
     * Funcion que nos permite agregar un ToDo
     * @param event 
     */
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
            // mode: 'cors',
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

    /**
     *  Funcion que nos permite actualizar un ToDo
     * @param  event 
     */
    const onEdit = (event) => {
        event.preventDefault();

        const request = {
            name: state.name,
            id_todo: item.id_todo,
            completed: item.completed,
            id_groupList: id.id_groupList,
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
                setState({ name: "" });
                formRef.current.reset();
            });
    };

    /**
     * Funcion que nos permite inhabilitar el boton en el caso que no se encuentre nada en el input
     * @param  name 
     * @returns Boolean 
     */
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
                <button class="btn btn-warning" disabled={wihtOutTodo(state.name)} onClick={onEdit}>
                    Actualizar
                </button>
            )}
            {!item.id_todo && (
                <button class="btn btn-primary" disabled={wihtOutTodo(state.name)} onClick={onAdd}>
                    Crear
                </button>
            )}
        </form>
    );
};

export default NewTodoForm;