package engine;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Itérateur de liste personnalisé auquel il est possible de rajouter des opérations
 * @author Raphaël
 */
public class CustomIterator<E> implements Iterator<E> {
	
	private List<E> list;
	private int index = 0;
	
	/**
	 * Construit un nouvel itérateur à partir d'une liste
	 * @author Raphaël
	 * @param l Liste d'objets
	 */
	public CustomIterator(Collection<E> l) {
		this.list = new ArrayList<E>(l);
	}

	/**
	 * Permet de déterminer si l'itérateur contient encore des éléments
	 * @author Raphaël
	 * @return true s'il contient encore des éléments, false sinon
	 */
	public boolean hasNext() {
		return this.index < this.list.size();
	}

	/**
	 * Retourne l'élément suivant de l'itérateur et positionne le pointeur sur l'index suivant
	 * @author Raphaël
	 * @return Element suivant si existant
	 * @throws NoSuchElementException s'il n'y a plus d'éléments
	 */
	public E next() {
		if (this.hasNext()) {
			return this.list.get(this.index++);
		}
		else {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Permet de savoir s'il reste des éléments dans le sens opposé de défilement de l'itérateur
	 * @author Raphaël
	 * @return true s'il reste des éléments, false sinon
	 */
	public boolean hasPrevious() {
		return this.index > 0;
	}

	/**
	 * Retourne l'élément précédent de l'itérateur et positionne le pointeur sur l'index précédent
	 * @author Raphaël
	 * @return Element précédent si existant
	 * @throws NoSuchElementException s'il n'y a plus d'éléments
	 */
	public E previous() {
		if (this.hasPrevious()) {
			return this.list.get(this.index--);
		}
		else {
			throw new NoSuchElementException();
		}
	}
	
	/**
	 * Permet de vérifier si un élément est contenu dans l'itérateur
	 * @author Raphaël
	 * @param e Objet à vérifier
	 * @return true si l'itérateur contient l'objet, false sinon
	 */
	public boolean contains(E e) {
		return this.list.contains(e);
	}
	
	/**
	 * Retourne la taille de l'itérateur
	 * @author Raphaël
	 * @return Taille de l'itérateur
	 */
	public int size() {
		return this.list.size();
	}

}
