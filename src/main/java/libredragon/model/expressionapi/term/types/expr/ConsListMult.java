
package libredragon.model.expressionapi.term.types.expr;



public final class ConsListMult extends libredragon.model.expressionapi.term.types.expr.ListMult implements tom.library.sl.Visitable  {
  
  private static String symbolName = "ConsListMult";


  private ConsListMult() {}
  private int hashCode;
  private static ConsListMult gomProto = new ConsListMult();
  
   private libredragon.model.expressionapi.term.types.Expr[] children;
                
private static boolean tom_equal_term_char(char t1, char t2) {return  t1==t2 ;}private static boolean tom_is_sort_char(char t) {return  true ;} private static boolean tom_equal_term_String(String t1, String t2) {return  t1.equals(t2) ;}private static boolean tom_is_sort_String(String t) {return  t instanceof String ;} private static boolean tom_equal_term_int(int t1, int t2) {return  t1==t2 ;}private static boolean tom_is_sort_int(int t) {return  true ;} private static boolean tom_equal_term_Expr(Object t1, Object t2) {return  (t1==t2) ;}private static boolean tom_is_sort_Expr(Object t) {return  (t instanceof libredragon.model.expressionapi.term.types.Expr) ;}private static boolean tom_is_fun_sym_ListMult( libredragon.model.expressionapi.term.types.Expr  t) {return  ((t instanceof libredragon.model.expressionapi.term.types.expr.ConsListMult) || (t instanceof libredragon.model.expressionapi.term.types.expr.EmptyListMult)) ;}private static  libredragon.model.expressionapi.term.types.Expr  tom_empty_list_ListMult() { return  libredragon.model.expressionapi.term.types.expr.EmptyListMult.make() ;}private static  libredragon.model.expressionapi.term.types.Expr  tom_cons_list_ListMult( libredragon.model.expressionapi.term.types.Expr  e,  libredragon.model.expressionapi.term.types.Expr  l) { return  libredragon.model.expressionapi.term.types.expr.ConsListMult.make(e,l) ;}private static  libredragon.model.expressionapi.term.types.Expr  tom_get_head_ListMult_Expr( libredragon.model.expressionapi.term.types.Expr  l) {return  l.getHeadListMult() ;}private static  libredragon.model.expressionapi.term.types.Expr  tom_get_tail_ListMult_Expr( libredragon.model.expressionapi.term.types.Expr  l) {return  l.getTailListMult() ;}private static boolean tom_is_empty_ListMult_Expr( libredragon.model.expressionapi.term.types.Expr  l) {return  l.isEmptyListMult() ;}   private static   libredragon.model.expressionapi.term.types.Expr  tom_append_list_ListMult( libredragon.model.expressionapi.term.types.Expr  l1,  libredragon.model.expressionapi.term.types.Expr  l2) {     if( l1.isEmptyListMult() ) {       return l2;     } else if( l2.isEmptyListMult() ) {       return l1;     } else if( ((l1 instanceof libredragon.model.expressionapi.term.types.expr.ConsListMult) || (l1 instanceof libredragon.model.expressionapi.term.types.expr.EmptyListMult)) ) {       if(  l1.getTailListMult() .isEmptyListMult() ) {         return  libredragon.model.expressionapi.term.types.expr.ConsListMult.make( l1.getHeadListMult() ,l2) ;       } else {         return  libredragon.model.expressionapi.term.types.expr.ConsListMult.make( l1.getHeadListMult() ,tom_append_list_ListMult( l1.getTailListMult() ,l2)) ;       }     } else {       return  libredragon.model.expressionapi.term.types.expr.ConsListMult.make(l1,l2) ;     }   }   private static   libredragon.model.expressionapi.term.types.Expr  tom_get_slice_ListMult( libredragon.model.expressionapi.term.types.Expr  begin,  libredragon.model.expressionapi.term.types.Expr  end, libredragon.model.expressionapi.term.types.Expr  tail) {     if( (begin==end) ) {       return tail;     } else if( (end==tail)  && ( end.isEmptyListMult()  ||  (end==tom_empty_list_ListMult()) )) {       /* code to avoid a call to make, and thus to avoid looping during list-matching */       return begin;     }     return  libredragon.model.expressionapi.term.types.expr.ConsListMult.make((( ((begin instanceof libredragon.model.expressionapi.term.types.expr.ConsListMult) || (begin instanceof libredragon.model.expressionapi.term.types.expr.EmptyListMult)) )? begin.getHeadListMult() :begin),( libredragon.model.expressionapi.term.types.Expr )tom_get_slice_ListMult((( ((begin instanceof libredragon.model.expressionapi.term.types.expr.ConsListMult) || (begin instanceof libredragon.model.expressionapi.term.types.expr.EmptyListMult)) )? begin.getTailListMult() :tom_empty_list_ListMult()),end,tail)) ;   }   






























































































































































































  private libredragon.model.expressionapi.term.types.Expr _HeadListMult;
  private libredragon.model.expressionapi.term.types.Expr _TailListMult;

  /**
   * Constructor that builds a term rooted by ConsListMult
   *
   * @return a term rooted by ConsListMult
   */

    public static libredragon.model.expressionapi.term.types.Expr make(libredragon.model.expressionapi.term.types.Expr head, libredragon.model.expressionapi.term.types.Expr tail) {
  if (true) {if (head.isEmptyListMult()) { return tail; }
if (tail.isEmptyListMult()) { return head; }
if (head.isConsListMult()) { return make(head.getHeadListMult(),make(head.getTailListMult(),tail)); }
}if (true) {if (tail.isConsListMult()) {
  if (0 < head.compareTo(tail.getHeadListMult())) {
    libredragon.model.expressionapi.term.types.Expr tmpHd = head;
    head = tail.getHeadListMult();
    tail = tom_cons_list_ListMult(tmpHd,tom_cons_list_ListMult(tail.getTailListMult(),tom_empty_list_ListMult()));
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
  
  private static ConsListMult realMake(libredragon.model.expressionapi.term.types.Expr _HeadListMult, libredragon.model.expressionapi.term.types.Expr _TailListMult) {

    // use the proto as a model
    gomProto.initHashCode( _HeadListMult,  _TailListMult);
    return (ConsListMult) factory.build(gomProto);

  }

  /**
   * Initializes attributes and hashcode of the class
   *
   * @param  _HeadListMult
   * @param _TailListMult
   * @param hashCode hashCode of ConsListMult
   */
  private void init(libredragon.model.expressionapi.term.types.Expr _HeadListMult, libredragon.model.expressionapi.term.types.Expr _TailListMult, int hashCode) {
    this._HeadListMult = _HeadListMult;
    this._TailListMult = _TailListMult;

    this.hashCode = hashCode;
  }

  /**
   * Initializes attributes and hashcode of the class
   *
   * @param  _HeadListMult
   * @param _TailListMult
   */
  private void initHashCode(libredragon.model.expressionapi.term.types.Expr _HeadListMult, libredragon.model.expressionapi.term.types.Expr _TailListMult) {
    this._HeadListMult = _HeadListMult;
    this._TailListMult = _TailListMult;

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
    return "ConsListMult";
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
    ConsListMult clone = new ConsListMult();
    clone.init( _HeadListMult,  _TailListMult, hashCode);
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
    ConsListMult tco = (ConsListMult) ao;
    int _HeadListMultCmp = (this._HeadListMult).compareToLPO(tco._HeadListMult);
    if(_HeadListMultCmp != 0) {
      return _HeadListMultCmp;
    }

    int _TailListMultCmp = (this._TailListMult).compareToLPO(tco._TailListMult);
    if(_TailListMultCmp != 0) {
      return _TailListMultCmp;
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
    ConsListMult tco = (ConsListMult) ao;
    int _HeadListMultCmp = (this._HeadListMult).compareTo(tco._HeadListMult);
    if(_HeadListMultCmp != 0) {
      return _HeadListMultCmp;
    }

    int _TailListMultCmp = (this._TailListMult).compareTo(tco._TailListMult);
    if(_TailListMultCmp != 0) {
      return _TailListMultCmp;
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
   * @return true if obj is a ConsListMult and its members are equal, else false
   */
  public final boolean equivalent(shared.SharedObject obj) {
    if(obj instanceof ConsListMult) {

      ConsListMult peer = (ConsListMult) obj;
      return _HeadListMult==peer._HeadListMult && _TailListMult==peer._TailListMult && true;
    }
    return false;
  }


   //Expr interface
  /**
   * Returns true if the term is rooted by the symbol ConsListMult
   *
   * @return true, because this is rooted by ConsListMult
   */
  @Override
  public boolean isConsListMult() {
    return true;
  }
  
  /**
   * Returns the attribute libredragon.model.expressionapi.term.types.Expr
   *
   * @return the attribute libredragon.model.expressionapi.term.types.Expr
   */
  @Override
  public libredragon.model.expressionapi.term.types.Expr getHeadListMult() {
    return _HeadListMult;
  }

  /**
   * Sets and returns the attribute libredragon.model.expressionapi.term.types.Expr
   *
   * @param set_arg the argument to set
   * @return the attribute libredragon.model.expressionapi.term.types.Expr which just has been set
   */
  @Override
  public libredragon.model.expressionapi.term.types.Expr setHeadListMult(libredragon.model.expressionapi.term.types.Expr set_arg) {
    return make(set_arg, _TailListMult);
  }
  
  /**
   * Returns the attribute libredragon.model.expressionapi.term.types.Expr
   *
   * @return the attribute libredragon.model.expressionapi.term.types.Expr
   */
  @Override
  public libredragon.model.expressionapi.term.types.Expr getTailListMult() {
    return _TailListMult;
  }

  /**
   * Sets and returns the attribute libredragon.model.expressionapi.term.types.Expr
   *
   * @param set_arg the argument to set
   * @return the attribute libredragon.model.expressionapi.term.types.Expr which just has been set
   */
  @Override
  public libredragon.model.expressionapi.term.types.Expr setTailListMult(libredragon.model.expressionapi.term.types.Expr set_arg) {
    return make(_HeadListMult, set_arg);
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
      new aterm.ATerm[] {getHeadListMult().toATerm(), getTailListMult().toATerm()});
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
    b = (-141609034<<8);
    c = getArity();
    /* -------------------------------------- handle most of the key */
    /* ------------------------------------ handle the last 11 bytes */
    a += (_HeadListMult.hashCode() << 8);
    a += (_TailListMult.hashCode());

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
