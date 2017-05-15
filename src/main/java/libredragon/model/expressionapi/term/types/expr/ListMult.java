
package libredragon.model.expressionapi.term.types.expr;



public abstract class ListMult extends libredragon.model.expressionapi.term.types.Expr implements java.util.Collection<libredragon.model.expressionapi.term.types.Expr>  {


  /**
   * Returns the number of arguments of the variadic operator
   *
   * @return the number of arguments of the variadic operator
   */
  @Override
  public int length() {
    if(this instanceof libredragon.model.expressionapi.term.types.expr.ConsListMult) {
      libredragon.model.expressionapi.term.types.Expr tl = this.getTailListMult();
      if (tl instanceof ListMult) {
        return 1+((ListMult)tl).length();
      } else {
        return 2;
      }
    } else {
      return 0;
    }
  }

  public static libredragon.model.expressionapi.term.types.Expr fromArray(libredragon.model.expressionapi.term.types.Expr[] array) {
    libredragon.model.expressionapi.term.types.Expr res = libredragon.model.expressionapi.term.types.expr.EmptyListMult.make();
    for(int i = array.length; i>0;) {
      res = libredragon.model.expressionapi.term.types.expr.ConsListMult.make(array[--i],res);
    }
    return res;
  }

  /**
   * Inverses the term if it is a list
   *
   * @return the inverted term if it is a list, otherwise the term itself
   */
  @Override
  public libredragon.model.expressionapi.term.types.Expr reverse() {
    if(this instanceof libredragon.model.expressionapi.term.types.expr.ConsListMult) {
      libredragon.model.expressionapi.term.types.Expr cur = this;
      libredragon.model.expressionapi.term.types.Expr rev = libredragon.model.expressionapi.term.types.expr.EmptyListMult.make();
      while(cur instanceof libredragon.model.expressionapi.term.types.expr.ConsListMult) {
        rev = libredragon.model.expressionapi.term.types.expr.ConsListMult.make(cur.getHeadListMult(),rev);
        cur = cur.getTailListMult();
      }

      if(!(cur instanceof libredragon.model.expressionapi.term.types.expr.EmptyListMult)) {
        rev = libredragon.model.expressionapi.term.types.expr.ConsListMult.make(cur,rev);
      }

      return rev;
    } else {
      return this;
    }
  }

  /**
   * Appends an element
   *
   * @param element element which has to be added
   * @return the term with the added element
   */
  public libredragon.model.expressionapi.term.types.Expr append(libredragon.model.expressionapi.term.types.Expr element) {
    if(this instanceof libredragon.model.expressionapi.term.types.expr.ConsListMult) {
      libredragon.model.expressionapi.term.types.Expr tl = this.getTailListMult();
      if (tl instanceof ListMult) {
        return libredragon.model.expressionapi.term.types.expr.ConsListMult.make(this.getHeadListMult(),((ListMult)tl).append(element));
      } else {

        return libredragon.model.expressionapi.term.types.expr.ConsListMult.make(this.getHeadListMult(),libredragon.model.expressionapi.term.types.expr.ConsListMult.make(tl,element));

      }
    } else {
      return libredragon.model.expressionapi.term.types.expr.ConsListMult.make(element,this);
    }
  }

  /**
   * Appends a string representation of this term to the buffer given as argument.
   *
   * @param buffer the buffer to which a string represention of this term is appended.
   */
  @Override
  public void toStringBuilder(java.lang.StringBuilder buffer) {
    buffer.append("ListMult(");
    if(this instanceof libredragon.model.expressionapi.term.types.expr.ConsListMult) {
      libredragon.model.expressionapi.term.types.Expr cur = this;
      while(cur instanceof libredragon.model.expressionapi.term.types.expr.ConsListMult) {
        libredragon.model.expressionapi.term.types.Expr elem = cur.getHeadListMult();
        cur = cur.getTailListMult();
        elem.toStringBuilder(buffer);

        if(cur instanceof libredragon.model.expressionapi.term.types.expr.ConsListMult) {
          buffer.append(",");
        }
      }
      if(!(cur instanceof libredragon.model.expressionapi.term.types.expr.EmptyListMult)) {
        buffer.append(",");
        cur.toStringBuilder(buffer);
      }
    }
    buffer.append(")");
  }

  /**
   * Returns an ATerm representation of this term.
   *
   * @return an ATerm representation of this term.
   */
  public aterm.ATerm toATerm() {
    aterm.ATerm res = atermFactory.makeList();
    if(this instanceof libredragon.model.expressionapi.term.types.expr.ConsListMult) {
      libredragon.model.expressionapi.term.types.Expr tail = this.getTailListMult();
      res = atermFactory.makeList(getHeadListMult().toATerm(),(aterm.ATermList)tail.toATerm());
    }
    return res;
  }

  /**
   * Apply a conversion on the ATerm contained in the String and returns a libredragon.model.expressionapi.term.types.Expr from it
   *
   * @param trm ATerm to convert into a Gom term
   * @param atConv ATerm Converter used to convert the ATerm
   * @return the Gom term
   */
  public static libredragon.model.expressionapi.term.types.Expr fromTerm(aterm.ATerm trm, tom.library.utils.ATermConverter atConv) {
    trm = atConv.convert(trm);
    if(trm instanceof aterm.ATermAppl) {
      aterm.ATermAppl appl = (aterm.ATermAppl) trm;
      if("ListMult".equals(appl.getName())) {
        libredragon.model.expressionapi.term.types.Expr res = libredragon.model.expressionapi.term.types.expr.EmptyListMult.make();

        aterm.ATerm array[] = appl.getArgumentArray();
        for(int i = array.length-1; i>=0; --i) {
          libredragon.model.expressionapi.term.types.Expr elem = libredragon.model.expressionapi.term.types.Expr.fromTerm(array[i],atConv);
          res = libredragon.model.expressionapi.term.types.expr.ConsListMult.make(elem,res);
        }
        return res;
      }
    }

    if(trm instanceof aterm.ATermList) {
      aterm.ATermList list = (aterm.ATermList) trm;
      libredragon.model.expressionapi.term.types.Expr res = libredragon.model.expressionapi.term.types.expr.EmptyListMult.make();
      try {
        while(!list.isEmpty()) {
          libredragon.model.expressionapi.term.types.Expr elem = libredragon.model.expressionapi.term.types.Expr.fromTerm(list.getFirst(),atConv);
          res = libredragon.model.expressionapi.term.types.expr.ConsListMult.make(elem,res);
          list = list.getNext();
        }
      } catch(IllegalArgumentException e) {
        // returns null when the fromATerm call failed
        return null;
      }
      return res.reverse();
    }

    return null;
  }

  /*
   * Checks if the Collection contains all elements of the parameter Collection
   *
   * @param c the Collection of elements to check
   * @return true if the Collection contains all elements of the parameter, otherwise false
   */
  public boolean containsAll(java.util.Collection c) {
    java.util.Iterator it = c.iterator();
    while(it.hasNext()) {
      if(!this.contains(it.next())) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks if libredragon.model.expressionapi.term.types.Expr contains a specified object
   *
   * @param o object whose presence is tested
   * @return true if libredragon.model.expressionapi.term.types.Expr contains the object, otherwise false
   */
  public boolean contains(Object o) {
    libredragon.model.expressionapi.term.types.Expr cur = this;
    if(o==null) { return false; }
    if(cur instanceof libredragon.model.expressionapi.term.types.expr.ConsListMult) {
      while(cur instanceof libredragon.model.expressionapi.term.types.expr.ConsListMult) {
        if( o.equals(cur.getHeadListMult()) ) {
          return true;
        }
        cur = cur.getTailListMult();
      }
      if(!(cur instanceof libredragon.model.expressionapi.term.types.expr.EmptyListMult)) {
        if( o.equals(cur) ) {
          return true;
        }
      }
    }
    return false;
  }

  //public boolean equals(Object o) { return this == o; }

  //public int hashCode() { return hashCode(); }

  /**
   * Checks the emptiness
   *
   * @return true if empty, otherwise false
   */
  public boolean isEmpty() { return isEmptyListMult() ; }

  public java.util.Iterator<libredragon.model.expressionapi.term.types.Expr> iterator() {
    return new java.util.Iterator<libredragon.model.expressionapi.term.types.Expr>() {
      libredragon.model.expressionapi.term.types.Expr list = ListMult.this;

      public boolean hasNext() {
        return list!=null && !list.isEmptyListMult();
      }

      public libredragon.model.expressionapi.term.types.Expr next() {
        if(list.isEmptyListMult()) {
          throw new java.util.NoSuchElementException();
        }
        if(list.isConsListMult()) {
          libredragon.model.expressionapi.term.types.Expr head = list.getHeadListMult();
          list = list.getTailListMult();
          return head;
        } else {
          // we are in this case only if domain=codomain
          // thus, the cast is safe
          Object res = list;
          list = null;
          return (libredragon.model.expressionapi.term.types.Expr)res;
        }
      }

      public void remove() {
        throw new UnsupportedOperationException("Not yet implemented");
      }
    };

  }

  public boolean add(libredragon.model.expressionapi.term.types.Expr o) {
    throw new UnsupportedOperationException("This object "+this.getClass().getName()+" is not mutable");
  }

  public boolean addAll(java.util.Collection<? extends libredragon.model.expressionapi.term.types.Expr> c) {
    throw new UnsupportedOperationException("This object "+this.getClass().getName()+" is not mutable");
  }

  public boolean remove(Object o) {
    throw new UnsupportedOperationException("This object "+this.getClass().getName()+" is not mutable");
  }

  public void clear() {
    throw new UnsupportedOperationException("This object "+this.getClass().getName()+" is not mutable");
  }

  public boolean removeAll(java.util.Collection c) {
    throw new UnsupportedOperationException("This object "+this.getClass().getName()+" is not mutable");
  }

  public boolean retainAll(java.util.Collection c) {
    throw new UnsupportedOperationException("This object "+this.getClass().getName()+" is not mutable");
  }

  /**
   * Returns the size of the collection
   *
   * @return the size of the collection
   */
  public int size() { return length(); }

  /**
   * Returns an array containing the elements of the collection
   *
   * @return an array of elements
   */
  public Object[] toArray() {
    int size = this.length();
    Object[] array = new Object[size];
    int i=0;
    if(this instanceof libredragon.model.expressionapi.term.types.expr.ConsListMult) {
      libredragon.model.expressionapi.term.types.Expr cur = this;
      while(cur instanceof libredragon.model.expressionapi.term.types.expr.ConsListMult) {
        libredragon.model.expressionapi.term.types.Expr elem = cur.getHeadListMult();
        array[i] = elem;
        cur = cur.getTailListMult();
        i++;
      }
      if(!(cur instanceof libredragon.model.expressionapi.term.types.expr.EmptyListMult)) {
        array[i] = cur;
      }
    }
    return array;
  }

  @SuppressWarnings("unchecked")
  public <T> T[] toArray(T[] array) {
    int size = this.length();
    if (array.length < size) {
      array = (T[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), size);
    } else if (array.length > size) {
      array[size] = null;
    }
    int i=0;
    if(this instanceof libredragon.model.expressionapi.term.types.expr.ConsListMult) {
      libredragon.model.expressionapi.term.types.Expr cur = this;
      while(cur instanceof libredragon.model.expressionapi.term.types.expr.ConsListMult) {
        libredragon.model.expressionapi.term.types.Expr elem = cur.getHeadListMult();
        array[i] = (T)elem;
        cur = cur.getTailListMult();
        i++;
      }
      if(!(cur instanceof libredragon.model.expressionapi.term.types.expr.EmptyListMult)) {
        array[i] = (T)cur;
      }
    }
    return array;
  }

  /*
   * to get a Collection for an immutable list
   */
  public java.util.Collection<libredragon.model.expressionapi.term.types.Expr> getCollection() {
    return new CollectionListMult(this);
  }

  public java.util.Collection<libredragon.model.expressionapi.term.types.Expr> getCollectionListMult() {
    return new CollectionListMult(this);
  }

  /************************************************************
   * private static class
   ************************************************************/
  private static class CollectionListMult implements java.util.Collection<libredragon.model.expressionapi.term.types.Expr> {
    private ListMult list;

    public ListMult getExpr() {
      return list;
    }

    public CollectionListMult(ListMult list) {
      this.list = list;
    }

    /**
     * generic
     */
  public boolean addAll(java.util.Collection<? extends libredragon.model.expressionapi.term.types.Expr> c) {
    boolean modified = false;
    java.util.Iterator<? extends libredragon.model.expressionapi.term.types.Expr> it = c.iterator();
    while(it.hasNext()) {
      modified = modified || add(it.next());
    }
    return modified;
  }

  /**
   * Checks if the collection contains an element
   *
   * @param o element whose presence has to be checked
   * @return true if the element is found, otherwise false
   */
  public boolean contains(Object o) {
    return getExpr().contains(o);
  }

  /**
   * Checks if the collection contains elements given as parameter
   *
   * @param c elements whose presence has to be checked
   * @return true all the elements are found, otherwise false
   */
  public boolean containsAll(java.util.Collection<?> c) {
    return getExpr().containsAll(c);
  }

  /**
   * Checks if an object is equal
   *
   * @param o object which is compared
   * @return true if objects are equal, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    return getExpr().equals(o);
  }

  /**
   * Returns the hashCode
   *
   * @return the hashCode
   */
  @Override
  public int hashCode() {
    return getExpr().hashCode();
  }

  /**
   * Returns an iterator over the elements in the collection
   *
   * @return an iterator over the elements in the collection
   */
  public java.util.Iterator<libredragon.model.expressionapi.term.types.Expr> iterator() {
    return getExpr().iterator();
  }

  /**
   * Return the size of the collection
   *
   * @return the size of the collection
   */
  public int size() {
    return getExpr().size();
  }

  /**
   * Returns an array containing all of the elements in this collection.
   *
   * @return an array of elements
   */
  public Object[] toArray() {
    return getExpr().toArray();
  }

  /**
   * Returns an array containing all of the elements in this collection.
   *
   * @param array array which will contain the result
   * @return an array of elements
   */
  public <T> T[] toArray(T[] array) {
    return getExpr().toArray(array);
  }

/*
  public <T> T[] toArray(T[] array) {
    int size = getExpr().length();
    if (array.length < size) {
      array = (T[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), size);
    } else if (array.length > size) {
      array[size] = null;
    }
    int i=0;
    for(java.util.Iterator it=iterator() ; it.hasNext() ; i++) {
        array[i] = (T)it.next();
    }
    return array;
  }
*/
    /**
     * Collection
     */

    /**
     * Adds an element to the collection
     *
     * @param o element to add to the collection
     * @return true if it is a success
     */
    public boolean add(libredragon.model.expressionapi.term.types.Expr o) {
      list = (ListMult)libredragon.model.expressionapi.term.types.expr.ConsListMult.make(o,list);
      return true;
    }

    /**
     * Removes all of the elements from this collection
     */
    public void clear() {
      list = (ListMult)libredragon.model.expressionapi.term.types.expr.EmptyListMult.make();
    }

    /**
     * Tests the emptiness of the collection
     *
     * @return true if the collection is empty
     */
    public boolean isEmpty() {
      return list.isEmptyListMult();
    }

    public boolean remove(Object o) {
      throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean removeAll(java.util.Collection<?> c) {
      throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean retainAll(java.util.Collection<?> c) {
      throw new UnsupportedOperationException("Not yet implemented");
    }

  }


}
