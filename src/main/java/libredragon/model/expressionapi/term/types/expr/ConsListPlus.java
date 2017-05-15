
package libredragon.model.expressionapi.term.types.expr;



public final class ConsListPlus extends libredragon.model.expressionapi.term.types.expr.ListPlus implements tom.library.sl.Visitable  {
  
  private static String symbolName = "ConsListPlus";


  private ConsListPlus() {}
  private int hashCode;
  private static ConsListPlus gomProto = new ConsListPlus();
  
   private libredragon.model.expressionapi.term.types.Expr[] children;
                
private static boolean tom_equal_term_char(char t1, char t2) {return  t1==t2 ;}private static boolean tom_is_sort_char(char t) {return  true ;} private static boolean tom_equal_term_String(String t1, String t2) {return  t1.equals(t2) ;}private static boolean tom_is_sort_String(String t) {return  t instanceof String ;} private static boolean tom_equal_term_int(int t1, int t2) {return  t1==t2 ;}private static boolean tom_is_sort_int(int t) {return  true ;} private static boolean tom_equal_term_Expr(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_Expr(Object t) {return  (t instanceof libredragon.model.expressionapi.term.types.Expr) ;}private static boolean tom_is_fun_sym_ListPlus( libredragon.model.expressionapi.term.types.Expr  t) {return  ((t instanceof libredragon.model.expressionapi.term.types.expr.ConsListPlus) || (t instanceof libredragon.model.expressionapi.term.types.expr.EmptyListPlus)) ;}private static  libredragon.model.expressionapi.term.types.Expr  tom_empty_list_ListPlus() { return  libredragon.model.expressionapi.term.types.expr.EmptyListPlus.make() ;}private static  libredragon.model.expressionapi.term.types.Expr  tom_cons_list_ListPlus( libredragon.model.expressionapi.term.types.Expr  e,  libredragon.model.expressionapi.term.types.Expr  l) { return  libredragon.model.expressionapi.term.types.expr.ConsListPlus.make(e,l) ;}private static  libredragon.model.expressionapi.term.types.Expr  tom_get_head_ListPlus_Expr( libredragon.model.expressionapi.term.types.Expr  l) {return  l.getHeadListPlus() ;}private static  libredragon.model.expressionapi.term.types.Expr  tom_get_tail_ListPlus_Expr( libredragon.model.expressionapi.term.types.Expr  l) {return  l.getTailListPlus() ;}private static boolean tom_is_empty_ListPlus_Expr( libredragon.model.expressionapi.term.types.Expr  l) {return  l.isEmptyListPlus() ;}   private static   libredragon.model.expressionapi.term.types.Expr  tom_append_list_ListPlus( libredragon.model.expressionapi.term.types.Expr  l1,  libredragon.model.expressionapi.term.types.Expr  l2) {     if( l1.isEmptyListPlus() ) {       return l2;     } else if( l2.isEmptyListPlus() ) {       return l1;     } else if( ((l1 instanceof libredragon.model.expressionapi.term.types.expr.ConsListPlus) || (l1 instanceof libredragon.model.expressionapi.term.types.expr.EmptyListPlus)) ) {       if(  l1.getTailListPlus() .isEmptyListPlus() ) {         return  libredragon.model.expressionapi.term.types.expr.ConsListPlus.make( l1.getHeadListPlus() ,l2) ;       } else {         return  libredragon.model.expressionapi.term.types.expr.ConsListPlus.make( l1.getHeadListPlus() ,tom_append_list_ListPlus( l1.getTailListPlus() ,l2)) ;       }     } else {       return  libredragon.model.expressionapi.term.types.expr.ConsListPlus.make(l1,l2) ;     }   }   private static   libredragon.model.expressionapi.term.types.Expr  tom_get_slice_ListPlus( libredragon.model.expressionapi.term.types.Expr  begin,  libredragon.model.expressionapi.term.types.Expr  end, libredragon.model.expressionapi.term.types.Expr  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyListPlus()  ||  (end==tom_empty_list_ListPlus()) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  libredragon.model.expressionapi.term.types.expr.ConsListPlus.make((( ((begin instanceof libredragon.model.expressionapi.term.types.expr.ConsListPlus) || (begin instanceof libredragon.model.expressionapi.term.types.expr.EmptyListPlus)) )? begin.getHeadListPlus() :begin),( libredragon.model.expressionapi.term.types.Expr )tom_get_slice_ListPlus((( ((begin instanceof libredragon.model.expressionapi.term.types.expr.ConsListPlus) || (begin instanceof libredragon.model.expressionapi.term.types.expr.EmptyListPlus)) )? begin.getTailListPlus() :tom_empty_list_ListPlus()),end,tail)) ;   }   






























































































































































































  private libredragon.model.expressionapi.term.types.Expr _HeadListPlus;
  private libredragon.model.expressionapi.term.types.Expr _TailListPlus;

  /**
   * Constructor that builds a term rooted by ConsListPlus
   *
   * @return a term rooted by ConsListPlus
   */

    public static libredragon.model.expressionapi.term.types.Expr make(libredragon.model.expressionapi.term.types.Expr head, libredragon.model.expressionapi.term.types.Expr tail) {
  if (true) {if (head.isEmptyListPlus()) { return tail; }
if (tail.isEmptyListPlus()) { return head; }
if (head.isConsListPlus()) { return make(head.getHeadListPlus(),make(head.getTailListPlus(),tail)); }
}if (true) {if (tail.isConsListPlus()) {
  if (0 < head.compareTo(tail.getHeadListPlus())) {
    libredragon.model.expressionapi.term.types.Expr tmpHd = head;
    head = tail.getHeadListPlus();
    tail = tom_cons_list_ListPlus(tmpHd,tom_cons_list_ListPlus(tail.getTailListPlus(),tom_empty_list_ListPlus()));
  }
} else {
  if (0 < head.compareTo(tail)) {
    libredragon.model.expressionapi.term.types.Expr tmpHd = head;
    head = tail;
    tail = tmpHd;
  }
}
}
      return realMake( head,  tail);
    }
  
  private static ConsListPlus realMake(libredragon.model.expressionapi.term.types.Expr _HeadListPlus, libredragon.model.expressionapi.term.types.Expr _TailListPlus) {

    // use the proto as a model
    gomProto.initHashCode( _HeadListPlus,  _TailListPlus);
    return (ConsListPlus) factory.build(gomProto);

  }

  /**
   * Initializes attributes and hashcode of the class
   *
   * @param  _HeadListPlus
   * @param _TailListPlus
   * @param hashCode hashCode of ConsListPlus
   */
  private void init(libredragon.model.expressionapi.term.types.Expr _HeadListPlus, libredragon.model.expressionapi.term.types.Expr _TailListPlus, int hashCode) {
    this._HeadListPlus = _HeadListPlus;
    this._TailListPlus = _TailListPlus;

    this.hashCode = hashCode;
  }

  /**
   * Initializes attributes and hashcode of the class
   *
   * @param  _HeadListPlus
   * @param _TailListPlus
   */
  private void initHashCode(libredragon.model.expressionapi.term.types.Expr _HeadListPlus, libredragon.model.expressionapi.term.types.Expr _TailListPlus) {
    this._HeadListPlus = _HeadListPlus;
    this._TailListPlus = _TailListPlus;

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
    return "ConsListPlus";
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
    ConsListPlus clone = new ConsListPlus();
    clone.init( _HeadListPlus,  _TailListPlus, hashCode);
    return clone;
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
    ConsListPlus tco = (ConsListPlus) ao;
    int _HeadListPlusCmp = (this._HeadListPlus).compareToLPO(tco._HeadListPlus);
    if(_HeadListPlusCmp != 0) {
      return _HeadListPlusCmp;
    }

    int _TailListPlusCmp = (this._TailListPlus).compareToLPO(tco._TailListPlus);
    if(_TailListPlusCmp != 0) {
      return _TailListPlusCmp;
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
    ConsListPlus tco = (ConsListPlus) ao;
    int _HeadListPlusCmp = (this._HeadListPlus).compareTo(tco._HeadListPlus);
    if(_HeadListPlusCmp != 0) {
      return _HeadListPlusCmp;
    }

    int _TailListPlusCmp = (this._TailListPlus).compareTo(tco._TailListPlus);
    if(_TailListPlusCmp != 0) {
      return _TailListPlusCmp;
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
   * @return true if obj is a ConsListPlus and its members are equal, else false
   */
  public final boolean equivalent(shared.SharedObject obj) {
    if(obj instanceof ConsListPlus) {

      ConsListPlus peer = (ConsListPlus) obj;
      return _HeadListPlus==peer._HeadListPlus && _TailListPlus==peer._TailListPlus && true;
    }
    return false;
  }


   //Expr interface
  /**
   * Returns true if the term is rooted by the symbol ConsListPlus
   *
   * @return true, because this is rooted by ConsListPlus
   */
  @Override
  public boolean isConsListPlus() {
    return true;
  }
  
  /**
   * Returns the attribute libredragon.model.expressionapi.term.types.Expr
   *
   * @return the attribute libredragon.model.expressionapi.term.types.Expr
   */
  @Override
  public libredragon.model.expressionapi.term.types.Expr getHeadListPlus() {
    return _HeadListPlus;
  }

  /**
   * Sets and returns the attribute libredragon.model.expressionapi.term.types.Expr
   *
   * @param set_arg the argument to set
   * @return the attribute libredragon.model.expressionapi.term.types.Expr which just has been set
   */
  @Override
  public libredragon.model.expressionapi.term.types.Expr setHeadListPlus(libredragon.model.expressionapi.term.types.Expr set_arg) {
    return make(set_arg, _TailListPlus);
  }
  
  /**
   * Returns the attribute libredragon.model.expressionapi.term.types.Expr
   *
   * @return the attribute libredragon.model.expressionapi.term.types.Expr
   */
  @Override
  public libredragon.model.expressionapi.term.types.Expr getTailListPlus() {
    return _TailListPlus;
  }

  /**
   * Sets and returns the attribute libredragon.model.expressionapi.term.types.Expr
   *
   * @param set_arg the argument to set
   * @return the attribute libredragon.model.expressionapi.term.types.Expr which just has been set
   */
  @Override
  public libredragon.model.expressionapi.term.types.Expr setTailListPlus(libredragon.model.expressionapi.term.types.Expr set_arg) {
    return make(_HeadListPlus, set_arg);
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
      new aterm.ATerm[] {getHeadListPlus().toATerm(), getTailListPlus().toATerm()});
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
    return getChildren().length;
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
    return getChildren()[index];
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
    
      tom.library.sl.Visitable[] children = getChildren();
      libredragon.model.expressionapi.term.types.Expr[] new_children = new libredragon.model.expressionapi.term.types.Expr[children.length];
      for(int i =0; i<children.length; i++) {
        new_children[i] = ((libredragon.model.expressionapi.term.types.Expr) children[i]); 
      }
     new_children[index] = (libredragon.model.expressionapi.term.types.Expr) v;
     return fromArray(new_children);
                  
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
    if (children.length == getChildCount() ) {
      
               libredragon.model.expressionapi.term.types.Expr[] typed_children = new libredragon.model.expressionapi.term.types.Expr[children.length];
              for (int i=0; i<children.length; i++) {
                typed_children[i] = (libredragon.model.expressionapi.term.types.Expr) children[i]; 
              }
              return fromArray(typed_children);
              
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
    
        if (children == null) {
          children = toArray(new libredragon.model.expressionapi.term.types.Expr[]{});
        }
        return java.util.Arrays.copyOf(children,children.length);
      
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
    b = (-1656934079<<8);
    c = getArity();
    /* -------------------------------------- handle most of the key */
    /* ------------------------------------ handle the last 11 bytes */
    a += (_HeadListPlus.hashCode() << 8);
    a += (_TailListPlus.hashCode());

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
