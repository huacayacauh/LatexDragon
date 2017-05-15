
package libredragon.model.expressionapi.term.types.expr;



public final class Mult extends libredragon.model.expressionapi.term.types.Expr implements tom.library.sl.Visitable  {
  
  private static String symbolName = "Mult";


  private Mult() {}
  private int hashCode;
  private static Mult gomProto = new Mult();
    private libredragon.model.expressionapi.term.types.Expr _e1;
  private libredragon.model.expressionapi.term.types.Expr _e2;

  /**
   * Constructor that builds a term rooted by Mult
   *
   * @return a term rooted by Mult
   */

  public static Mult make(libredragon.model.expressionapi.term.types.Expr _e1, libredragon.model.expressionapi.term.types.Expr _e2) {

    // use the proto as a model
    gomProto.initHashCode( _e1,  _e2);
    return (Mult) factory.build(gomProto);

  }

  /**
   * Initializes attributes and hashcode of the class
   *
   * @param  _e1
   * @param _e2
   * @param hashCode hashCode of Mult
   */
  private void init(libredragon.model.expressionapi.term.types.Expr _e1, libredragon.model.expressionapi.term.types.Expr _e2, int hashCode) {
    this._e1 = _e1;
    this._e2 = _e2;

    this.hashCode = hashCode;
  }

  /**
   * Initializes attributes and hashcode of the class
   *
   * @param  _e1
   * @param _e2
   */
  private void initHashCode(libredragon.model.expressionapi.term.types.Expr _e1, libredragon.model.expressionapi.term.types.Expr _e2) {
    this._e1 = _e1;
    this._e2 = _e2;

    this.hashCode = hashFunction();
  }

  /* name and arity */

  /**
   * Returns the name of the symbol
   *
   * @return the name of the symbol
   */
  @Override
  public String symbolName() {
    return "Mult";
  }

  /**
   * Returns the arity of the symbol
   *
   * @return the arity of the symbol
   */
  private int getArity() {
    return 2;
  }

  /**
   * Copy the object and returns the copy
   *
   * @return a clone of the SharedObject
   */
  public shared.SharedObject duplicate() {
    Mult clone = new Mult();
    clone.init( _e1,  _e2, hashCode);
    return clone;
  }
  
  /**
   * Appends a string representation of this term to the buffer given as argument.
   *
   * @param buffer the buffer to which a string represention of this term is appended.
   */
  @Override
  public void toStringBuilder(java.lang.StringBuilder buffer) {
    buffer.append("Mult(");
    _e1.toStringBuilder(buffer);
buffer.append(",");
    _e2.toStringBuilder(buffer);

    buffer.append(")");
  }


  /**
   * Compares two terms. This functions implements a total lexicographic path ordering.
   *
   * @param o object to which this term is compared
   * @return a negative integer, zero, or a positive integer as this
   *         term is less than, equal to, or greater than the argument
   * @throws ClassCastException in case of invalid arguments
   * @throws RuntimeException if unable to compare children
   */
  @Override
  public int compareToLPO(Object o) {
    /*
     * We do not want to compare with any object, only members of the module
     * In case of invalid argument, throw a ClassCastException, as the java api
     * asks for it
     */
    libredragon.model.expressionapi.term.TermAbstractType ao = (libredragon.model.expressionapi.term.TermAbstractType) o;
    /* return 0 for equality */
    if (ao == this) { return 0; }
    /* compare the symbols */
    int symbCmp = this.symbolName().compareTo(ao.symbolName());
    if (symbCmp != 0) { return symbCmp; }
    /* compare the children */
    Mult tco = (Mult) ao;
    int _e1Cmp = (this._e1).compareToLPO(tco._e1);
    if(_e1Cmp != 0) {
      return _e1Cmp;
    }

    int _e2Cmp = (this._e2).compareToLPO(tco._e2);
    if(_e2Cmp != 0) {
      return _e2Cmp;
    }

    throw new RuntimeException("Unable to compare");
  }

 /**
   * Compares two terms. This functions implements a total order.
   *
   * @param o object to which this term is compared
   * @return a negative integer, zero, or a positive integer as this
   *         term is less than, equal to, or greater than the argument
   * @throws ClassCastException in case of invalid arguments
   * @throws RuntimeException if unable to compare children
   */
  @Override
  public int compareTo(Object o) {
    /*
     * We do not want to compare with any object, only members of the module
     * In case of invalid argument, throw a ClassCastException, as the java api
     * asks for it
     */
    libredragon.model.expressionapi.term.TermAbstractType ao = (libredragon.model.expressionapi.term.TermAbstractType) o;
    /* return 0 for equality */
    if (ao == this) { return 0; }
    /* use the hash values to discriminate */

    if(hashCode != ao.hashCode()) { return (hashCode < ao.hashCode())?-1:1; }

    /* If not, compare the symbols : back to the normal order */
    int symbCmp = this.symbolName().compareTo(ao.symbolName());
    if (symbCmp != 0) { return symbCmp; }
    /* last resort: compare the children */
    Mult tco = (Mult) ao;
    int _e1Cmp = (this._e1).compareTo(tco._e1);
    if(_e1Cmp != 0) {
      return _e1Cmp;
    }

    int _e2Cmp = (this._e2).compareTo(tco._e2);
    if(_e2Cmp != 0) {
      return _e2Cmp;
    }

    throw new RuntimeException("Unable to compare");
  }

 //shared.SharedObject
  /**
   * Returns hashCode
   *
   * @return hashCode
   */
  @Override
  public final int hashCode() {
    return hashCode;
  }

  /**
   * Checks if a SharedObject is equivalent to the current object
   *
   * @param obj SharedObject to test
   * @return true if obj is a Mult and its members are equal, else false
   */
  public final boolean equivalent(shared.SharedObject obj) {
    if(obj instanceof Mult) {

      Mult peer = (Mult) obj;
      return _e1==peer._e1 && _e2==peer._e2 && true;
    }
    return false;
  }


   //Expr interface
  /**
   * Returns true if the term is rooted by the symbol Mult
   *
   * @return true, because this is rooted by Mult
   */
  @Override
  public boolean isMult() {
    return true;
  }
  
  /**
   * Returns the attribute libredragon.model.expressionapi.term.types.Expr
   *
   * @return the attribute libredragon.model.expressionapi.term.types.Expr
   */
  @Override
  public libredragon.model.expressionapi.term.types.Expr gete1() {
    return _e1;
  }

  /**
   * Sets and returns the attribute libredragon.model.expressionapi.term.types.Expr
   *
   * @param set_arg the argument to set
   * @return the attribute libredragon.model.expressionapi.term.types.Expr which just has been set
   */
  @Override
  public libredragon.model.expressionapi.term.types.Expr sete1(libredragon.model.expressionapi.term.types.Expr set_arg) {
    return make(set_arg, _e2);
  }
  
  /**
   * Returns the attribute libredragon.model.expressionapi.term.types.Expr
   *
   * @return the attribute libredragon.model.expressionapi.term.types.Expr
   */
  @Override
  public libredragon.model.expressionapi.term.types.Expr gete2() {
    return _e2;
  }

  /**
   * Sets and returns the attribute libredragon.model.expressionapi.term.types.Expr
   *
   * @param set_arg the argument to set
   * @return the attribute libredragon.model.expressionapi.term.types.Expr which just has been set
   */
  @Override
  public libredragon.model.expressionapi.term.types.Expr sete2(libredragon.model.expressionapi.term.types.Expr set_arg) {
    return make(_e1, set_arg);
  }
  
  /* AbstractType */
  /**
   * Returns an ATerm representation of this term.
   *
   * @return an ATerm representation of this term.
   */
  @Override
  public aterm.ATerm toATerm() {
    aterm.ATerm res = super.toATerm();
    if(res != null) {
      // the super class has produced an ATerm (may be a variadic operator)
      return res;
    }
    return atermFactory.makeAppl(
      atermFactory.makeAFun(symbolName(),getArity(),false),
      new aterm.ATerm[] {gete1().toATerm(), gete2().toATerm()});
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
      if(symbolName.equals(appl.getName()) && !appl.getAFun().isQuoted()) {
        return make(
libredragon.model.expressionapi.term.types.Expr.fromTerm(appl.getArgument(0),atConv), libredragon.model.expressionapi.term.types.Expr.fromTerm(appl.getArgument(1),atConv)
        );
      }
    }
    return null;
  }

  /* Visitable */
  /**
   * Returns the number of children of the term
   *
   * @return the number of children of the term
   */
  public int getChildCount() {
    return 2;
  }

  /**
   * Returns the child at the specified index
   *
   * @param index index of the child to return; must be
             nonnegative and less than the childCount
   * @return the child at the specified index
   * @throws IndexOutOfBoundsException if the index out of range
   */
  public tom.library.sl.Visitable getChildAt(int index) {
        switch(index) {
      case 0: return _e1;
      case 1: return _e2;
      default: throw new IndexOutOfBoundsException();
 }
 }

  /**
   * Set the child at the specified index
   *
   * @param index index of the child to set; must be
             nonnegative and less than the childCount
   * @param v child to set at the specified index
   * @return the child which was just set
   * @throws IndexOutOfBoundsException if the index out of range
   */
  public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable v) {
        switch(index) {
      case 0: return make((libredragon.model.expressionapi.term.types.Expr) v, _e2);
      case 1: return make(_e1, (libredragon.model.expressionapi.term.types.Expr) v);
      default: throw new IndexOutOfBoundsException();
 }
  }

  /**
   * Set children to the term
   *
   * @param children array of children to set
   * @return an array of children which just were set
   * @throws IndexOutOfBoundsException if length of "children" is different than 2
   */
  @SuppressWarnings("unchecked")
  public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {
    if (children.length == getChildCount()  && children[0] instanceof libredragon.model.expressionapi.term.types.Expr && children[1] instanceof libredragon.model.expressionapi.term.types.Expr) {
      return make((libredragon.model.expressionapi.term.types.Expr) children[0], (libredragon.model.expressionapi.term.types.Expr) children[1]);
    } else {
      throw new IndexOutOfBoundsException();
    }
  }

  /**
   * Returns the whole children of the term
   *
   * @return the children of the term
   */
  public tom.library.sl.Visitable[] getChildren() {
    return new tom.library.sl.Visitable[] { _e1,  _e2};
  }

    /**
     * Compute a hashcode for this term.
     * (for internal use)
     *
     * @return a hash value
     */
  protected int hashFunction() {
    int a, b, c;
    /* Set up the internal state */
    a = 0x9e3779b9; /* the golden ratio; an arbitrary value */
    b = (744314031<<8);
    c = getArity();
    /* -------------------------------------- handle most of the key */
    /* ------------------------------------ handle the last 11 bytes */
    a += (_e1.hashCode() << 8);
    a += (_e2.hashCode());

    a -= b; a -= c; a ^= (c >> 13);
    b -= c; b -= a; b ^= (a << 8);
    c -= a; c -= b; c ^= (b >> 13);
    a -= b; a -= c; a ^= (c >> 12);
    b -= c; b -= a; b ^= (a << 16);
    c -= a; c -= b; c ^= (b >> 5);
    a -= b; a -= c; a ^= (c >> 3);
    b -= c; b -= a; b ^= (a << 10);
    c -= a; c -= b; c ^= (b >> 15);
    /* ------------------------------------------- report the result */
    return c;
  }

  /**
    * function that returns functional version of the current operator
    * need for initializing the Enumerator
    */
  public static tom.library.enumerator.F<tom.library.enumerator.Enumeration<libredragon.model.expressionapi.term.types.Expr>,tom.library.enumerator.F<tom.library.enumerator.Enumeration<libredragon.model.expressionapi.term.types.Expr>,tom.library.enumerator.Enumeration<libredragon.model.expressionapi.term.types.Expr>>> funMake() {
    return 
        new tom.library.enumerator.F<tom.library.enumerator.Enumeration<libredragon.model.expressionapi.term.types.Expr>,tom.library.enumerator.F<tom.library.enumerator.Enumeration<libredragon.model.expressionapi.term.types.Expr>,tom.library.enumerator.Enumeration<libredragon.model.expressionapi.term.types.Expr>>>() {
          public tom.library.enumerator.F<tom.library.enumerator.Enumeration<libredragon.model.expressionapi.term.types.Expr>,tom.library.enumerator.Enumeration<libredragon.model.expressionapi.term.types.Expr>> apply(final tom.library.enumerator.Enumeration<libredragon.model.expressionapi.term.types.Expr> t1) {
            return 
        new tom.library.enumerator.F<tom.library.enumerator.Enumeration<libredragon.model.expressionapi.term.types.Expr>,tom.library.enumerator.Enumeration<libredragon.model.expressionapi.term.types.Expr>>() {
          public tom.library.enumerator.Enumeration<libredragon.model.expressionapi.term.types.Expr> apply(final tom.library.enumerator.Enumeration<libredragon.model.expressionapi.term.types.Expr> t2) {
            return tom.library.enumerator.Enumeration.apply(tom.library.enumerator.Enumeration.apply(tom.library.enumerator.Enumeration.singleton((tom.library.enumerator.F<libredragon.model.expressionapi.term.types.Expr,tom.library.enumerator.F<libredragon.model.expressionapi.term.types.Expr,libredragon.model.expressionapi.term.types.Expr>>) 
        new tom.library.enumerator.F<libredragon.model.expressionapi.term.types.Expr,tom.library.enumerator.F<libredragon.model.expressionapi.term.types.Expr,libredragon.model.expressionapi.term.types.Expr>>() {
          public tom.library.enumerator.F<libredragon.model.expressionapi.term.types.Expr,libredragon.model.expressionapi.term.types.Expr> apply(final libredragon.model.expressionapi.term.types.Expr t1) {
            return 
        new tom.library.enumerator.F<libredragon.model.expressionapi.term.types.Expr,libredragon.model.expressionapi.term.types.Expr>() {
          public libredragon.model.expressionapi.term.types.Expr apply(final libredragon.model.expressionapi.term.types.Expr t2) {
            return make(t1,t2);
          }
        };
          }
        }),t1),t2).pay();
          }
        };
          }
        };
  }

}
