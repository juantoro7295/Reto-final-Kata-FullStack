const reducer = (state, action) => {
    switch (action.type) {
        case "update-item":
            const todoUpItem = state.todo;
            const listUpdateEdit = todoUpItem.list.map((item) => {
                if (item.id_todo === action.item.id_todo) {
                    return action.item;
                }
                return item;
            });
            todoUpItem.list = listUpdateEdit;
            todoUpItem.item = {};
            return { ...state, todo: todoUpItem };
        case "delete-item":
            const todoUpDelete = state.todo;
            const listUpdate = todoUpDelete.list.filter((item) => {
                return item.id_todo !== action.id_todo;
            });
            todoUpDelete.list = listUpdate;
            return { ...state, todo: todoUpDelete };
        case "update-list":
            const todoUpList = state.todo;
            todoUpList.list = action.list;
            return { ...state, todo: todoUpList };
        case "edit-item":
            const todoUpEdit = state.todo;
            todoUpEdit.item = action.item;
            return { ...state, todo: todoUpEdit };
        case "add-item":
            const todoUp = state.todo.list;
            todoUp.push(action.item);
            return { ...state, todo: { list: todoUp, item: {} } };

        case "add-listTodo":
            const todoList = state.todoList.list;
            todoList.push(action.item);
            return { ...state, listTodo: { todoList: todoList, item: {} } };

        case "update-groupList":
            const listTodos = state.groupList;
            listTodos.list = action.list;
            return { ...state, groupList: listTodos };

        case "delete-groupList":
            const listDelete = state.todo;
            const listUpdater = listDelete.list.filter((item) => {
                return item.id_groupList !== action.id_groupList;
            });
            listDelete.list = listUpdater;
            return { ...state, todo: listDelete };
        default:
            return state;
    }
};
export default reducer;