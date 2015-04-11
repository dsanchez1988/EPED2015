package Ifaces;

public interface BTreeIF<T> {
	public int PREORDER = 0;
	public int INORDER = 1;
	public int POSTORDER = 2;
	public int LRBREADTH = 3;
	public int RLBREADTH = 4;

	/***
	 * Devuelve el elemento raiz del arbol.* @return el elemento raiz del arbol.
	 */
	public T getRoot();

	/***
	 * Devuelve el subarbol izquierdo o null si no existe.* @return el subarbol
	 * izquierdo.
	 */
	public BTreeIF<T> getLeftChild();

	/***
	 * Devuelve el subarbol derecho o null si no existe.* @return el subarbol
	 * derecho.
	 */
	public BTreeIF<T> getRightChild();

	/**
	 * Establece el elemento raiz.
	 * 
	 * @param element
	 *            El elemento a establecer.
	 */
	public void setRoot(T element);

	/**
	 * Establece el subarbol izquierdo.
	 * 
	 * @param tree
	 *            el arbol a esablecer.
	 */
	public void setLeftChild(BTreeIF<T> tree);

	/**
	 * Establece el subarbol derecho.
	 * 
	 * @param tree
	 *            el arbol a esablecer.
	 */
	public void setRightChild(BTreeIF<T> tree);

	/**
	 * Borra el subarbol izquierdo.
	 */
	public void removeLeftChild();

	/**
	 * Borra el subarbol derecho.
	 */
	public void removeRightChild();

	/**
	 * Devuelce cierto si el arbol es un nodo hoja.
	 * 
	 * @return cierto si el arbol es un nodo hoja.
	 */
	public boolean isLeaf();

	/**
	 * Devuelve cierto si el arbol es vacio.
	 * 
	 * @return cierto si el arbol es vacio.
	 */
	public boolean isEmpty();

	/**
	 * Devuelve cierto si el arbol contiene el elemento.
	 * 
	 * @param element
	 *            El elemento buscado.
	 * @return cierto si el arbol contiene el elemento.
	 */
	public boolean contains(T element);

	/**
	 * Devuelve un iterador para el arbol.
	 * 
	 * @param traversalType
	 *            El tipo de recorrido.
	 * @return un iterador para el arbol.
	 */
	public IteratorIF<T> getIterator(int traversalType);
}
