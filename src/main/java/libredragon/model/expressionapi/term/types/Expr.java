
package libredragon.model.expressionapi.term.types;


public abstract class Expr extends libredragon.model.expressionapi.term.TermAbstractType  {
  /**
   * Sole constructor.  (For invocation by subclass
   * constructors, typically implicit.)
   */
  protected Expr() {}



  /**
   * Returns true if the term is rooted by the symbol Plus
   *
   * @return true if the term is rooted by the symbol Plus
   */
  public boolean isPlus() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol Mult
   *
   * @return true if the term is rooted by the symbol Mult
   */
  public boolean isMult() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol Eq
   *
   * @return true if the term is rooted by the symbol Eq
   */
  public boolean isEq() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol Parenthesis
   *
   * @return true if the term is rooted by the symbol Parenthesis
   */
  public boolean isParenthesis() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol Sqrt
   *
   * @return true if the term is rooted by the symbol Sqrt
   */
  public boolean isSqrt() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol MoinsU
   *
   * @return true if the term is rooted by the symbol MoinsU
   */
  public boolean isMoinsU() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol MoinsB
   *
   * @return true if the term is rooted by the symbol MoinsB
   */
  public boolean isMoinsB() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol Non
   *
   * @return true if the term is rooted by the symbol Non
   */
  public boolean isNon() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol Factorial
   *
   * @return true if the term is rooted by the symbol Factorial
   */
  public boolean isFactorial() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol Or
   *
   * @return true if the term is rooted by the symbol Or
   */
  public boolean isOr() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol And
   *
   * @return true if the term is rooted by the symbol And
   */
  public boolean isAnd() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol Diff
   *
   * @return true if the term is rooted by the symbol Diff
   */
  public boolean isDiff() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol Inf
   *
   * @return true if the term is rooted by the symbol Inf
   */
  public boolean isInf() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol Sup
   *
   * @return true if the term is rooted by the symbol Sup
   */
  public boolean isSup() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol Infegal
   *
   * @return true if the term is rooted by the symbol Infegal
   */
  public boolean isInfegal() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol Supegal
   *
   * @return true if the term is rooted by the symbol Supegal
   */
  public boolean isSupegal() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol Divide
   *
   * @return true if the term is rooted by the symbol Divide
   */
  public boolean isDivide() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol Power
   *
   * @return true if the term is rooted by the symbol Power
   */
  public boolean isPower() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol Epsilon
   *
   * @return true if the term is rooted by the symbol Epsilon
   */
  public boolean isEpsilon() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol Nombre
   *
   * @return true if the term is rooted by the symbol Nombre
   */
  public boolean isNombre() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol Litteral
   *
   * @return true if the term is rooted by the symbol Litteral
   */
  public boolean isLitteral() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol ConsListPlus
   *
   * @return true if the term is rooted by the symbol ConsListPlus
   */
  public boolean isConsListPlus() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol EmptyListPlus
   *
   * @return true if the term is rooted by the symbol EmptyListPlus
   */
  public boolean isEmptyListPlus() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol ConsListMult
   *
   * @return true if the term is rooted by the symbol ConsListMult
   */
  public boolean isConsListMult() {
    return false;
  }

  /**
   * Returns true if the term is rooted by the symbol EmptyListMult
   *
   * @return true if the term is rooted by the symbol EmptyListMult
   */
  public boolean isEmptyListMult() {
    return false;
  }

  /**
   * Returns the subterm corresponding to the slot HeadListMult
   *
   * @return the subterm corresponding to the slot HeadListMult
   */
  public libredragon.model.expressionapi.term.types.Expr getHeadListMult() {
    throw new UnsupportedOperationException("This Expr has no HeadListMult");
  }

  /**
   * Returns a new term where the subterm corresponding to the slot HeadListMult
   * is replaced by the term given in argument.
   * Note that there is no side-effect: a new term is returned and the original term is left unchanged
   *
   * @param _arg the value of the new subterm
   * @return a new term where the subterm corresponding to the slot HeadListMult is replaced by _arg
   */
  public Expr setHeadListMult(libredragon.model.expressionapi.term.types.Expr _arg) {
    throw new UnsupportedOperationException("This Expr has no HeadListMult");
  }

  /**
   * Returns the subterm corresponding to the slot name
   *
   * @return the subterm corresponding to the slot name
   */
  public String getname() {
    throw new UnsupportedOperationException("This Expr has no name");
  }

  /**
   * Returns a new term where the subterm corresponding to the slot name
   * is replaced by the term given in argument.
   * Note that there is no side-effect: a new term is returned and the original term is left unchanged
   *
   * @param _arg the value of the new subterm
   * @return a new term where the subterm corresponding to the slot name is replaced by _arg
   */
  public Expr setname(String _arg) {
    throw new UnsupportedOperationException("This Expr has no name");
  }

  /**
   * Returns the subterm corresponding to the slot TailListMult
   *
   * @return the subterm corresponding to the slot TailListMult
   */
  public libredragon.model.expressionapi.term.types.Expr getTailListMult() {
    throw new UnsupportedOperationException("This Expr has no TailListMult");
  }

  /**
   * Returns a new term where the subterm corresponding to the slot TailListMult
   * is replaced by the term given in argument.
   * Note that there is no side-effect: a new term is returned and the original term is left unchanged
   *
   * @param _arg the value of the new subterm
   * @return a new term where the subterm corresponding to the slot TailListMult is replaced by _arg
   */
  public Expr setTailListMult(libredragon.model.expressionapi.term.types.Expr _arg) {
    throw new UnsupportedOperationException("This Expr has no TailListMult");
  }

  /**
   * Returns the subterm corresponding to the slot e1
   *
   * @return the subterm corresponding to the slot e1
   */
  public libredragon.model.expressionapi.term.types.Expr gete1() {
    throw new UnsupportedOperationException("This Expr has no e1");
  }

  /**
   * Returns a new term where the subterm corresponding to the slot e1
   * is replaced by the term given in argument.
   * Note that there is no side-effect: a new term is returned and the original term is left unchanged
   *
   * @param _arg the value of the new subterm
   * @return a new term where the subterm corresponding to the slot e1 is replaced by _arg
   */
  public Expr sete1(libredragon.model.expressionapi.term.types.Expr _arg) {
    throw new UnsupportedOperationException("This Expr has no e1");
  }

  /**
   * Returns the subterm corresponding to the slot i
   *
   * @return the subterm corresponding to the slot i
   */
  public int geti() {
    throw new UnsupportedOperationException("This Expr has no i");
  }

  /**
   * Returns a new term where the subterm corresponding to the slot i
   * is replaced by the term given in argument.
   * Note that there is no side-effect: a new term is returned and the original term is left unchanged
   *
   * @param _arg the value of the new subterm
   * @return a new term where the subterm corresponding to the slot i is replaced by _arg
   */
  public Expr seti(int _arg) {
    throw new UnsupportedOperationException("This Expr has no i");
  }

  /**
   * Returns the subterm corresponding to the slot TailListPlus
   *
   * @return the subterm corresponding to the slot TailListPlus
   */
  public libredragon.model.expressionapi.term.types.Expr getTailListPlus() {
    throw new UnsupportedOperationException("This Expr has no TailListPlus");
  }

  /**
   * Returns a new term where the subterm corresponding to the slot TailListPlus
   * is replaced by the term given in argument.
   * Note that there is no side-effect: a new term is returned and the original term is left unchanged
   *
   * @param _arg the value of the new subterm
   * @return a new term where the subterm corresponding to the slot TailListPlus is replaced by _arg
   */
  public Expr setTailListPlus(libredragon.model.expressionapi.term.types.Expr _arg) {
    throw new UnsupportedOperationException("This Expr has no TailListPlus");
  }

  /**
   * Returns the subterm corresponding to the slot e
   *
   * @return the subterm corresponding to the slot e
   */
  public libredragon.model.expressionapi.term.types.Expr gete() {
    throw new UnsupportedOperationException("This Expr has no e");
  }

  /**
   * Returns a new term where the subterm corresponding to the slot e
   * is replaced by the term given in argument.
   * Note that there is no side-effect: a new term is returned and the original term is left unchanged
   *
   * @param _arg the value of the new subterm
   * @return a new term where the subterm corresponding to the slot e is replaced by _arg
   */
  public Expr sete(libredragon.model.expressionapi.term.types.Expr _arg) {
    throw new UnsupportedOperationException("This Expr has no e");
  }

  /**
   * Returns the subterm corresponding to the slot e2
   *
   * @return the subterm corresponding to the slot e2
   */
  public libredragon.model.expressionapi.term.types.Expr gete2() {
    throw new UnsupportedOperationException("This Expr has no e2");
  }

  /**
   * Returns a new term where the subterm corresponding to the slot e2
   * is replaced by the term given in argument.
   * Note that there is no side-effect: a new term is returned and the original term is left unchanged
   *
   * @param _arg the value of the new subterm
   * @return a new term where the subterm corresponding to the slot e2 is replaced by _arg
   */
  public Expr sete2(libredragon.model.expressionapi.term.types.Expr _arg) {
    throw new UnsupportedOperationException("This Expr has no e2");
  }

  /**
   * Returns the subterm corresponding to the slot HeadListPlus
   *
   * @return the subterm corresponding to the slot HeadListPlus
   */
  public libredragon.model.expressionapi.term.types.Expr getHeadListPlus() {
    throw new UnsupportedOperationException("This Expr has no HeadListPlus");
  }

  /**
   * Returns a new term where the subterm corresponding to the slot HeadListPlus
   * is replaced by the term given in argument.
   * Note that there is no side-effect: a new term is returned and the original term is left unchanged
   *
   * @param _arg the value of the new subterm
   * @return a new term where the subterm corresponding to the slot HeadListPlus is replaced by _arg
   */
  public Expr setHeadListPlus(libredragon.model.expressionapi.term.types.Expr _arg) {
    throw new UnsupportedOperationException("This Expr has no HeadListPlus");
  }

  protected static tom.library.utils.IdConverter idConv = new tom.library.utils.IdConverter();

  /**
   * Returns an ATerm representation of this term.
   *
   * @return null to indicate to sub-classes that they have to work
   */
  public aterm.ATerm toATerm() {
    // returns null to indicate sub-classes that they have to work
    return null;
  }

  /**
   * Returns a libredragon.model.expressionapi.term.types.Expr from an ATerm without any conversion
   *
   * @param trm ATerm to handle to retrieve a Gom term
   * @return the term from the ATerm
   */
  public static libredragon.model.expressionapi.term.types.Expr fromTerm(aterm.ATerm trm) {
    return fromTerm(trm,idConv);
  }

  /**
   * Returns a libredragon.model.expressionapi.term.types.Expr from a String without any conversion
   *
   * @param s String containing the ATerm
   * @return the term from the String
   */
  public static libredragon.model.expressionapi.term.types.Expr fromString(String s) {
    return fromTerm(atermFactory.parse(s),idConv);
  }

  /**
   * Returns a libredragon.model.expressionapi.term.types.Expr from a Stream without any conversion
   *
   * @param stream stream containing the ATerm
   * @return the term from the Stream
   * @throws java.io.IOException if a problem occurs with the stream
   */
  public static libredragon.model.expressionapi.term.types.Expr fromStream(java.io.InputStream stream) throws java.io.IOException {
    return fromTerm(atermFactory.readFromFile(stream),idConv);
  }

  /**
   * Apply a conversion on the ATerm and returns a libredragon.model.expressionapi.term.types.Expr
   *
   * @param trm ATerm to convert into a Gom term
   * @param atConv ATermConverter used to convert the ATerm
   * @return the Gom term
   * @throws IllegalArgumentException
   */
  public static libredragon.model.expressionapi.term.types.Expr fromTerm(aterm.ATerm trm, tom.library.utils.ATermConverter atConv) {
    aterm.ATerm convertedTerm = atConv.convert(trm);
    libredragon.model.expressionapi.term.types.Expr tmp;
    java.util.ArrayList<libredragon.model.expressionapi.term.types.Expr> results = new java.util.ArrayList<libredragon.model.expressionapi.term.types.Expr>();

    tmp = libredragon.model.expressionapi.term.types.expr.Plus.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = libredragon.model.expressionapi.term.types.expr.Mult.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = libredragon.model.expressionapi.term.types.expr.Eq.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = libredragon.model.expressionapi.term.types.expr.Parenthesis.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = libredragon.model.expressionapi.term.types.expr.Sqrt.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = libredragon.model.expressionapi.term.types.expr.MoinsU.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = libredragon.model.expressionapi.term.types.expr.MoinsB.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = libredragon.model.expressionapi.term.types.expr.Non.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = libredragon.model.expressionapi.term.types.expr.Factorial.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = libredragon.model.expressionapi.term.types.expr.Or.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = libredragon.model.expressionapi.term.types.expr.And.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = libredragon.model.expressionapi.term.types.expr.Diff.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = libredragon.model.expressionapi.term.types.expr.Inf.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = libredragon.model.expressionapi.term.types.expr.Sup.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = libredragon.model.expressionapi.term.types.expr.Infegal.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = libredragon.model.expressionapi.term.types.expr.Supegal.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = libredragon.model.expressionapi.term.types.expr.Divide.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = libredragon.model.expressionapi.term.types.expr.Power.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = libredragon.model.expressionapi.term.types.expr.Epsilon.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = libredragon.model.expressionapi.term.types.expr.Nombre.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = libredragon.model.expressionapi.term.types.expr.Litteral.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = libredragon.model.expressionapi.term.types.expr.ConsListPlus.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = libredragon.model.expressionapi.term.types.expr.EmptyListPlus.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = libredragon.model.expressionapi.term.types.expr.ConsListMult.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = libredragon.model.expressionapi.term.types.expr.EmptyListMult.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = libredragon.model.expressionapi.term.types.expr.ListPlus.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    tmp = libredragon.model.expressionapi.term.types.expr.ListMult.fromTerm(convertedTerm,atConv);
    if(tmp!=null) {
      results.add(tmp);
    }
    switch(results.size()) {
      case 0:
        throw new IllegalArgumentException(trm + " is not a Expr");
      case 1:
        return results.get(0);
      default:
        java.util.logging.Logger.getLogger("Expr").log(java.util.logging.Level.WARNING,"There were many possibilities ({0}) in {1} but the first one was chosen: {2}",new Object[] {results.toString(), "libredragon.model.expressionapi.term.types.Expr", results.get(0).toString()});
        return results.get(0);
    }
  }

  /**
   * Apply a conversion on the ATerm contained in the String and returns a libredragon.model.expressionapi.term.types.Expr from it
   *
   * @param s String containing the ATerm
   * @param atConv ATerm Converter used to convert the ATerm
   * @return the Gom term
   */
  public static libredragon.model.expressionapi.term.types.Expr fromString(String s, tom.library.utils.ATermConverter atConv) {
    return fromTerm(atermFactory.parse(s),atConv);
  }

  /**
   * Apply a conversion on the ATerm contained in the Stream and returns a libredragon.model.expressionapi.term.types.Expr from it
   *
   * @param stream stream containing the ATerm
   * @param atConv ATerm Converter used to convert the ATerm
   * @return the Gom term
   */
  public static libredragon.model.expressionapi.term.types.Expr fromStream(java.io.InputStream stream, tom.library.utils.ATermConverter atConv) throws java.io.IOException {
    return fromTerm(atermFactory.readFromFile(stream),atConv);
  }

  /**
   * Returns the length of the list
   *
   * @return the length of the list
   * @throws IllegalArgumentException if the term is not a list
   */
  public int length() {
    throw new IllegalArgumentException(
      "This "+this.getClass().getName()+" is not a list");
  }

  /**
   * Returns an inverted term
   *
   * @return the inverted list
   * @throws IllegalArgumentException if the term is not a list
   */
  public libredragon.model.expressionapi.term.types.Expr reverse() {
    throw new IllegalArgumentException(
      "This "+this.getClass().getName()+" is not a list");
  }

  
  /**
   * Returns a Collection extracted from the term
   *
   * @return the collection
   * @throws UnsupportedOperationException if the term is not a list
   */
  public java.util.Collection<libredragon.model.expressionapi.term.types.Expr> getCollectionListPlus() {
    throw new UnsupportedOperationException("This Expr cannot be converted into a Collection");
  }
          
  /**
   * Returns a Collection extracted from the term
   *
   * @return the collection
   * @throws UnsupportedOperationException if the term is not a list
   */
  public java.util.Collection<libredragon.model.expressionapi.term.types.Expr> getCollectionListMult() {
    throw new UnsupportedOperationException("This Expr cannot be converted into a Collection");
  }
          
  /*
   * Initialize the (cyclic) data-structure
   * in order to generate/enumerate terms
   */

  protected static tom.library.enumerator.Enumeration<libredragon.model.expressionapi.term.types.Expr> enumExpr = null;
  public static final tom.library.enumerator.Enumeration<libredragon.model.expressionapi.term.types.Expr> tmpenumExpr = new tom.library.enumerator.Enumeration<libredragon.model.expressionapi.term.types.Expr>((tom.library.enumerator.LazyList<tom.library.enumerator.Finite<libredragon.model.expressionapi.term.types.Expr>>) null);

  public static tom.library.enumerator.Enumeration<libredragon.model.expressionapi.term.types.Expr> getEnumeration() {
    if(enumExpr == null) { 
      enumExpr = libredragon.model.expressionapi.term.types.expr.Plus.funMake().apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr).apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr)
        .plus(libredragon.model.expressionapi.term.types.expr.Mult.funMake().apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr).apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr))
        .plus(libredragon.model.expressionapi.term.types.expr.Eq.funMake().apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr).apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr))
        .plus(libredragon.model.expressionapi.term.types.expr.Parenthesis.funMake().apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr))
        .plus(libredragon.model.expressionapi.term.types.expr.Sqrt.funMake().apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr))
        .plus(libredragon.model.expressionapi.term.types.expr.MoinsU.funMake().apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr))
        .plus(libredragon.model.expressionapi.term.types.expr.MoinsB.funMake().apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr).apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr))
        .plus(libredragon.model.expressionapi.term.types.expr.Non.funMake().apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr))
        .plus(libredragon.model.expressionapi.term.types.expr.Factorial.funMake().apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr))
        .plus(libredragon.model.expressionapi.term.types.expr.Or.funMake().apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr).apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr))
        .plus(libredragon.model.expressionapi.term.types.expr.And.funMake().apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr).apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr))
        .plus(libredragon.model.expressionapi.term.types.expr.Diff.funMake().apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr).apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr))
        .plus(libredragon.model.expressionapi.term.types.expr.Inf.funMake().apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr).apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr))
        .plus(libredragon.model.expressionapi.term.types.expr.Sup.funMake().apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr).apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr))
        .plus(libredragon.model.expressionapi.term.types.expr.Infegal.funMake().apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr).apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr))
        .plus(libredragon.model.expressionapi.term.types.expr.Supegal.funMake().apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr).apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr))
        .plus(libredragon.model.expressionapi.term.types.expr.Divide.funMake().apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr).apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr))
        .plus(libredragon.model.expressionapi.term.types.expr.Power.funMake().apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr).apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr))
        .plus(libredragon.model.expressionapi.term.types.expr.Epsilon.funMake().apply(libredragon.model.expressionapi.term.types.Expr.tmpenumExpr))
        .plus(libredragon.model.expressionapi.term.types.expr.Nombre.funMake().apply(tom.library.enumerator.Combinators.makeint()))
        .plus(libredragon.model.expressionapi.term.types.expr.Litteral.funMake().apply(tom.library.enumerator.Combinators.makeString()));


      tmpenumExpr.p1 = new tom.library.enumerator.P1<tom.library.enumerator.LazyList<tom.library.enumerator.Finite<libredragon.model.expressionapi.term.types.Expr>>>() {
        public tom.library.enumerator.LazyList<tom.library.enumerator.Finite<libredragon.model.expressionapi.term.types.Expr>> _1() { return enumExpr.parts(); }
      };

    }
    return enumExpr;
  }

}
