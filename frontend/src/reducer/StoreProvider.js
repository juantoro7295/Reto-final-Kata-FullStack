import React, { useReducer } from "react";
import { initialState, Store } from "../App";
import reducer from "../reducer/reducer"

/**
 * Componente StoreProvider
 *
 * @author Juan Pablo Toro Hurtado.
 * @version 1.0.0
 * @since 1.0.0
 */

const StoreProvider = ({ children }) => {
  const [state, dispatch] = useReducer(reducer, initialState);
  return (
    <Store.Provider value={{ state, dispatch }}>{children}</Store.Provider>
  );
};
export default StoreProvider;