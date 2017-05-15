package libredragon.model;

import libredragon.model.expressionapi.term.types.*;
import tom.library.sl.*;
import java.util.*;
public  class ExpressionApi {

private static boolean tom_equal_term_Strategy(Object t1, Object t2) {
return  (t1.equals(t2)) ;
}
private static boolean tom_is_sort_Strategy(Object t) {
return  (t instanceof tom.library.sl.Strategy) ;
}
private static boolean tom_equal_term_Position(Object t1, Object t2) {
return  (t1.equals(t2)) ;
}
private static boolean tom_is_sort_Position(Object t) {
return  (t instanceof tom.library.sl.Position) ;
}
private static boolean tom_equal_term_int(int t1, int t2) {
return  t1==t2 ;
}
private static boolean tom_is_sort_int(int t) {
return  true ;
}
private static boolean tom_equal_term_char(char t1, char t2) {
return  t1==t2 ;
}
private static boolean tom_is_sort_char(char t) {
return  true ;
}
private static boolean tom_equal_term_String(String t1, String t2) {
return  t1.equals(t2) ;
}
private static boolean tom_is_sort_String(String t) {
return  t instanceof String ;
}
private static  tom.library.sl.Strategy  tom_make_mu( tom.library.sl.Strategy  var,  tom.library.sl.Strategy  v) { 
return ( new tom.library.sl.Mu(var,v) );
}
private static  tom.library.sl.Strategy  tom_make_MuVar( String  name) { 
return ( new tom.library.sl.MuVar(name) );
}
private static  tom.library.sl.Strategy  tom_make_Identity() { 
return ( new tom.library.sl.Identity() );
}
private static  tom.library.sl.Strategy  tom_make_One( tom.library.sl.Strategy  v) { 
return ( new tom.library.sl.One(v) );
}
private static  tom.library.sl.Strategy  tom_make_All( tom.library.sl.Strategy  v) { 
return ( new tom.library.sl.All(v) );
}
private static  tom.library.sl.Strategy  tom_make_Fail() { 
return ( new tom.library.sl.Fail() );
}
private static boolean tom_is_fun_sym_Sequence( tom.library.sl.Strategy  t) {
return ( t instanceof tom.library.sl.Sequence );
}
private static  tom.library.sl.Strategy  tom_empty_list_Sequence() { 
return  null ;
}
private static  tom.library.sl.Strategy  tom_cons_list_Sequence( tom.library.sl.Strategy  head,  tom.library.sl.Strategy  tail) { 
return  tom.library.sl.Sequence.make(head,tail) ;
}
private static  tom.library.sl.Strategy  tom_get_head_Sequence_Strategy( tom.library.sl.Strategy  t) {
return ( (tom.library.sl.Strategy)t.getChildAt(tom.library.sl.Sequence.FIRST) );
}
private static  tom.library.sl.Strategy  tom_get_tail_Sequence_Strategy( tom.library.sl.Strategy  t) {
return ( (tom.library.sl.Strategy)t.getChildAt(tom.library.sl.Sequence.THEN) );
}
private static boolean tom_is_empty_Sequence_Strategy( tom.library.sl.Strategy  t) {
return ( t == null );
}

  private static   tom.library.sl.Strategy  tom_append_list_Sequence( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {
    if(( l1 == null )) {
      return l2;
    } else if(( l2 == null )) {
      return l1;
    } else if(( l1 instanceof tom.library.sl.Sequence )) {
      if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ) == null )) {
        return  tom.library.sl.Sequence.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ),l2) ;
      } else {
        return  tom.library.sl.Sequence.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.FIRST) ),tom_append_list_Sequence(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Sequence.THEN) ),l2)) ;
      }
    } else {
      return  tom.library.sl.Sequence.make(l1,l2) ;
    }
  }
  private static   tom.library.sl.Strategy  tom_get_slice_Sequence( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {
    if( (begin.equals(end)) ) {
      return tail;
    } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals(tom_empty_list_Sequence())) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.library.sl.Sequence.make(((( begin instanceof tom.library.sl.Sequence ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Sequence(((( begin instanceof tom.library.sl.Sequence ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Sequence.THEN) ):tom_empty_list_Sequence()),end,tail)) ;
  }
  private static boolean tom_is_fun_sym_Choice( tom.library.sl.Strategy  t) {
return ( t instanceof tom.library.sl.Choice );
}
private static  tom.library.sl.Strategy  tom_empty_list_Choice() { 
return  null ;
}
private static  tom.library.sl.Strategy  tom_cons_list_Choice( tom.library.sl.Strategy  head,  tom.library.sl.Strategy  tail) { 
return  tom.library.sl.Choice.make(head,tail) ;
}
private static  tom.library.sl.Strategy  tom_get_head_Choice_Strategy( tom.library.sl.Strategy  t) {
return ( (tom.library.sl.Strategy)t.getChildAt(tom.library.sl.Choice.FIRST) );
}
private static  tom.library.sl.Strategy  tom_get_tail_Choice_Strategy( tom.library.sl.Strategy  t) {
return ( (tom.library.sl.Strategy)t.getChildAt(tom.library.sl.Choice.THEN) );
}
private static boolean tom_is_empty_Choice_Strategy( tom.library.sl.Strategy  t) {
return ( t ==null );
}

  private static   tom.library.sl.Strategy  tom_append_list_Choice( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {
    if(( l1 ==null )) {
      return l2;
    } else if(( l2 ==null )) {
      return l1;
    } else if(( l1 instanceof tom.library.sl.Choice )) {
      if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.THEN) ) ==null )) {
        return  tom.library.sl.Choice.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ),l2) ;
      } else {
        return  tom.library.sl.Choice.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.FIRST) ),tom_append_list_Choice(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.Choice.THEN) ),l2)) ;
      }
    } else {
      return  tom.library.sl.Choice.make(l1,l2) ;
    }
  }
  private static   tom.library.sl.Strategy  tom_get_slice_Choice( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {
    if( (begin.equals(end)) ) {
      return tail;
    } else if( (end.equals(tail))  && (( end ==null ) ||  (end.equals(tom_empty_list_Choice())) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.library.sl.Choice.make(((( begin instanceof tom.library.sl.Choice ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_Choice(((( begin instanceof tom.library.sl.Choice ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.Choice.THEN) ):tom_empty_list_Choice()),end,tail)) ;
  }
  private static boolean tom_is_fun_sym_SequenceId( tom.library.sl.Strategy  t) {
return ( t instanceof tom.library.sl.SequenceId );
}
private static  tom.library.sl.Strategy  tom_empty_list_SequenceId() { 
return  null ;
}
private static  tom.library.sl.Strategy  tom_cons_list_SequenceId( tom.library.sl.Strategy  head,  tom.library.sl.Strategy  tail) { 
return  tom.library.sl.SequenceId.make(head,tail) ;
}
private static  tom.library.sl.Strategy  tom_get_head_SequenceId_Strategy( tom.library.sl.Strategy  t) {
return ( (tom.library.sl.Strategy)t.getChildAt(tom.library.sl.SequenceId.FIRST) );
}
private static  tom.library.sl.Strategy  tom_get_tail_SequenceId_Strategy( tom.library.sl.Strategy  t) {
return ( (tom.library.sl.Strategy)t.getChildAt(tom.library.sl.SequenceId.THEN) );
}
private static boolean tom_is_empty_SequenceId_Strategy( tom.library.sl.Strategy  t) {
return ( t == null );
}

  private static   tom.library.sl.Strategy  tom_append_list_SequenceId( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {
    if(( l1 == null )) {
      return l2;
    } else if(( l2 == null )) {
      return l1;
    } else if(( l1 instanceof tom.library.sl.SequenceId )) {
      if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.THEN) ) == null )) {
        return  tom.library.sl.SequenceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.FIRST) ),l2) ;
      } else {
        return  tom.library.sl.SequenceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.FIRST) ),tom_append_list_SequenceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.SequenceId.THEN) ),l2)) ;
      }
    } else {
      return  tom.library.sl.SequenceId.make(l1,l2) ;
    }
  }
  private static   tom.library.sl.Strategy  tom_get_slice_SequenceId( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {
    if( (begin.equals(end)) ) {
      return tail;
    } else if( (end.equals(tail))  && (( end == null ) ||  (end.equals(tom_empty_list_SequenceId())) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.library.sl.SequenceId.make(((( begin instanceof tom.library.sl.SequenceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.SequenceId.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_SequenceId(((( begin instanceof tom.library.sl.SequenceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.SequenceId.THEN) ):tom_empty_list_SequenceId()),end,tail)) ;
  }
  private static boolean tom_is_fun_sym_ChoiceId( tom.library.sl.Strategy  t) {
return ( t instanceof tom.library.sl.ChoiceId );
}
private static  tom.library.sl.Strategy  tom_empty_list_ChoiceId() { 
return  null ;
}
private static  tom.library.sl.Strategy  tom_cons_list_ChoiceId( tom.library.sl.Strategy  head,  tom.library.sl.Strategy  tail) { 
return  tom.library.sl.ChoiceId.make(head,tail) ;
}
private static  tom.library.sl.Strategy  tom_get_head_ChoiceId_Strategy( tom.library.sl.Strategy  t) {
return ( (tom.library.sl.Strategy)t.getChildAt(tom.library.sl.ChoiceId.FIRST) );
}
private static  tom.library.sl.Strategy  tom_get_tail_ChoiceId_Strategy( tom.library.sl.Strategy  t) {
return ( (tom.library.sl.Strategy)t.getChildAt(tom.library.sl.ChoiceId.THEN) );
}
private static boolean tom_is_empty_ChoiceId_Strategy( tom.library.sl.Strategy  t) {
return ( t ==null );
}

  private static   tom.library.sl.Strategy  tom_append_list_ChoiceId( tom.library.sl.Strategy  l1,  tom.library.sl.Strategy  l2) {
    if(( l1 ==null )) {
      return l2;
    } else if(( l2 ==null )) {
      return l1;
    } else if(( l1 instanceof tom.library.sl.ChoiceId )) {
      if(( ( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.THEN) ) ==null )) {
        return  tom.library.sl.ChoiceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.FIRST) ),l2) ;
      } else {
        return  tom.library.sl.ChoiceId.make(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.FIRST) ),tom_append_list_ChoiceId(( (tom.library.sl.Strategy)l1.getChildAt(tom.library.sl.ChoiceId.THEN) ),l2)) ;
      }
    } else {
      return  tom.library.sl.ChoiceId.make(l1,l2) ;
    }
  }
  private static   tom.library.sl.Strategy  tom_get_slice_ChoiceId( tom.library.sl.Strategy  begin,  tom.library.sl.Strategy  end, tom.library.sl.Strategy  tail) {
    if( (begin.equals(end)) ) {
      return tail;
    } else if( (end.equals(tail))  && (( end ==null ) ||  (end.equals(tom_empty_list_ChoiceId())) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  tom.library.sl.ChoiceId.make(((( begin instanceof tom.library.sl.ChoiceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.ChoiceId.FIRST) ):begin),( tom.library.sl.Strategy )tom_get_slice_ChoiceId(((( begin instanceof tom.library.sl.ChoiceId ))?( (tom.library.sl.Strategy)begin.getChildAt(tom.library.sl.ChoiceId.THEN) ):tom_empty_list_ChoiceId()),end,tail)) ;
  }
  private static  tom.library.sl.Strategy  tom_make_OneId( tom.library.sl.Strategy  v) { 
return ( new tom.library.sl.OneId(v) );
}
private static  tom.library.sl.Strategy  tom_make_AllSeq( tom.library.sl.Strategy  s) { 
return ( new tom.library.sl.AllSeq(s) );
}
private static  tom.library.sl.Strategy  tom_make_AUCtl( tom.library.sl.Strategy  s1,  tom.library.sl.Strategy  s2) { 
return ( 
tom_make_mu(tom_make_MuVar("x"),tom_cons_list_Choice(s2,tom_cons_list_Choice(tom_cons_list_Sequence(tom_cons_list_Sequence(s1,tom_cons_list_Sequence(tom_make_All(tom_make_MuVar("x")),tom_empty_list_Sequence())),tom_cons_list_Sequence(tom_make_One(tom_make_Identity()),tom_empty_list_Sequence())),tom_empty_list_Choice()))))
;
}
private static  tom.library.sl.Strategy  tom_make_EUCtl( tom.library.sl.Strategy  s1,  tom.library.sl.Strategy  s2) { 
return ( 
tom_make_mu(tom_make_MuVar("x"),tom_cons_list_Choice(s2,tom_cons_list_Choice(tom_cons_list_Sequence(s1,tom_cons_list_Sequence(tom_make_One(tom_make_MuVar("x")),tom_empty_list_Sequence())),tom_empty_list_Choice()))))
;
}
private static  tom.library.sl.Strategy  tom_make_Try( tom.library.sl.Strategy  s) { 
return ( 
tom_cons_list_Choice(s,tom_cons_list_Choice(tom_make_Identity(),tom_empty_list_Choice())))
;
}
private static  tom.library.sl.Strategy  tom_make_Repeat( tom.library.sl.Strategy  s) { 
return ( 
tom_make_mu(tom_make_MuVar("_x"),tom_cons_list_Choice(tom_cons_list_Sequence(s,tom_cons_list_Sequence(tom_make_MuVar("_x"),tom_empty_list_Sequence())),tom_cons_list_Choice(tom_make_Identity(),tom_empty_list_Choice()))))
;
}
private static  tom.library.sl.Strategy  tom_make_TopDown( tom.library.sl.Strategy  v) { 
return ( 
tom_make_mu(tom_make_MuVar("_x"),tom_cons_list_Sequence(v,tom_cons_list_Sequence(tom_make_All(tom_make_MuVar("_x")),tom_empty_list_Sequence()))))
;
}
private static  tom.library.sl.Strategy  tom_make_OnceTopDown( tom.library.sl.Strategy  v) { 
return ( 
tom_make_mu(tom_make_MuVar("_x"),tom_cons_list_Choice(v,tom_cons_list_Choice(tom_make_One(tom_make_MuVar("_x")),tom_empty_list_Choice()))))
;
}
private static  tom.library.sl.Strategy  tom_make_RepeatId( tom.library.sl.Strategy  v) { 
return ( 
tom_make_mu(tom_make_MuVar("_x"),tom_cons_list_SequenceId(v,tom_cons_list_SequenceId(tom_make_MuVar("_x"),tom_empty_list_SequenceId()))))
;
}
private static  tom.library.sl.Strategy  tom_make_OnceTopDownId( tom.library.sl.Strategy  v) { 
return ( 
tom_make_mu(tom_make_MuVar("_x"),tom_cons_list_ChoiceId(v,tom_cons_list_ChoiceId(tom_make_OneId(tom_make_MuVar("_x")),tom_empty_list_ChoiceId()))))
;
}
private static boolean tom_equal_term_Expr(Object t1, Object t2) {
return  (t1==t2) ;
}
private static boolean tom_is_sort_Expr(Object t) {
return  (t instanceof libredragon.model.expressionapi.term.types.Expr) ;
}
private static boolean tom_is_fun_sym_Plus( libredragon.model.expressionapi.term.types.Expr  t) {
return  (t instanceof libredragon.model.expressionapi.term.types.expr.Plus) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_make_Plus( libredragon.model.expressionapi.term.types.Expr  t0,  libredragon.model.expressionapi.term.types.Expr  t1) { 
return  libredragon.model.expressionapi.term.types.expr.Plus.make(t0, t1) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_Plus_e1( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete1() ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_Plus_e2( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete2() ;
}
private static boolean tom_is_fun_sym_Mult( libredragon.model.expressionapi.term.types.Expr  t) {
return  (t instanceof libredragon.model.expressionapi.term.types.expr.Mult) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_make_Mult( libredragon.model.expressionapi.term.types.Expr  t0,  libredragon.model.expressionapi.term.types.Expr  t1) { 
return  libredragon.model.expressionapi.term.types.expr.Mult.make(t0, t1) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_Mult_e1( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete1() ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_Mult_e2( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete2() ;
}
private static boolean tom_is_fun_sym_Eq( libredragon.model.expressionapi.term.types.Expr  t) {
return  (t instanceof libredragon.model.expressionapi.term.types.expr.Eq) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_make_Eq( libredragon.model.expressionapi.term.types.Expr  t0,  libredragon.model.expressionapi.term.types.Expr  t1) { 
return  libredragon.model.expressionapi.term.types.expr.Eq.make(t0, t1) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_Eq_e1( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete1() ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_Eq_e2( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete2() ;
}
private static boolean tom_is_fun_sym_Parenthesis( libredragon.model.expressionapi.term.types.Expr  t) {
return  (t instanceof libredragon.model.expressionapi.term.types.expr.Parenthesis) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_make_Parenthesis( libredragon.model.expressionapi.term.types.Expr  t0) { 
return  libredragon.model.expressionapi.term.types.expr.Parenthesis.make(t0) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_Parenthesis_e( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete() ;
}
private static boolean tom_is_fun_sym_Sqrt( libredragon.model.expressionapi.term.types.Expr  t) {
return  (t instanceof libredragon.model.expressionapi.term.types.expr.Sqrt) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_make_Sqrt( libredragon.model.expressionapi.term.types.Expr  t0) { 
return  libredragon.model.expressionapi.term.types.expr.Sqrt.make(t0) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_Sqrt_e( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete() ;
}
private static boolean tom_is_fun_sym_MoinsU( libredragon.model.expressionapi.term.types.Expr  t) {
return  (t instanceof libredragon.model.expressionapi.term.types.expr.MoinsU) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_make_MoinsU( libredragon.model.expressionapi.term.types.Expr  t0) { 
return  libredragon.model.expressionapi.term.types.expr.MoinsU.make(t0) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_MoinsU_e( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete() ;
}
private static boolean tom_is_fun_sym_MoinsB( libredragon.model.expressionapi.term.types.Expr  t) {
return  (t instanceof libredragon.model.expressionapi.term.types.expr.MoinsB) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_make_MoinsB( libredragon.model.expressionapi.term.types.Expr  t0,  libredragon.model.expressionapi.term.types.Expr  t1) { 
return  libredragon.model.expressionapi.term.types.expr.MoinsB.make(t0, t1) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_MoinsB_e1( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete1() ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_MoinsB_e2( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete2() ;
}
private static boolean tom_is_fun_sym_Non( libredragon.model.expressionapi.term.types.Expr  t) {
return  (t instanceof libredragon.model.expressionapi.term.types.expr.Non) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_make_Non( libredragon.model.expressionapi.term.types.Expr  t0) { 
return  libredragon.model.expressionapi.term.types.expr.Non.make(t0) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_Non_e( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete() ;
}
private static boolean tom_is_fun_sym_Factorial( libredragon.model.expressionapi.term.types.Expr  t) {
return  (t instanceof libredragon.model.expressionapi.term.types.expr.Factorial) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_make_Factorial( libredragon.model.expressionapi.term.types.Expr  t0) { 
return  libredragon.model.expressionapi.term.types.expr.Factorial.make(t0) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_Factorial_e( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete() ;
}
private static boolean tom_is_fun_sym_Or( libredragon.model.expressionapi.term.types.Expr  t) {
return  (t instanceof libredragon.model.expressionapi.term.types.expr.Or) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_make_Or( libredragon.model.expressionapi.term.types.Expr  t0,  libredragon.model.expressionapi.term.types.Expr  t1) { 
return  libredragon.model.expressionapi.term.types.expr.Or.make(t0, t1) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_Or_e1( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete1() ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_Or_e2( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete2() ;
}
private static boolean tom_is_fun_sym_And( libredragon.model.expressionapi.term.types.Expr  t) {
return  (t instanceof libredragon.model.expressionapi.term.types.expr.And) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_make_And( libredragon.model.expressionapi.term.types.Expr  t0,  libredragon.model.expressionapi.term.types.Expr  t1) { 
return  libredragon.model.expressionapi.term.types.expr.And.make(t0, t1) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_And_e1( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete1() ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_And_e2( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete2() ;
}
private static boolean tom_is_fun_sym_Diff( libredragon.model.expressionapi.term.types.Expr  t) {
return  (t instanceof libredragon.model.expressionapi.term.types.expr.Diff) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_make_Diff( libredragon.model.expressionapi.term.types.Expr  t0,  libredragon.model.expressionapi.term.types.Expr  t1) { 
return  libredragon.model.expressionapi.term.types.expr.Diff.make(t0, t1) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_Diff_e1( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete1() ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_Diff_e2( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete2() ;
}
private static boolean tom_is_fun_sym_Inf( libredragon.model.expressionapi.term.types.Expr  t) {
return  (t instanceof libredragon.model.expressionapi.term.types.expr.Inf) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_make_Inf( libredragon.model.expressionapi.term.types.Expr  t0,  libredragon.model.expressionapi.term.types.Expr  t1) { 
return  libredragon.model.expressionapi.term.types.expr.Inf.make(t0, t1) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_Inf_e1( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete1() ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_Inf_e2( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete2() ;
}
private static boolean tom_is_fun_sym_Sup( libredragon.model.expressionapi.term.types.Expr  t) {
return  (t instanceof libredragon.model.expressionapi.term.types.expr.Sup) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_make_Sup( libredragon.model.expressionapi.term.types.Expr  t0,  libredragon.model.expressionapi.term.types.Expr  t1) { 
return  libredragon.model.expressionapi.term.types.expr.Sup.make(t0, t1) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_Sup_e1( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete1() ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_Sup_e2( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete2() ;
}
private static boolean tom_is_fun_sym_Infegal( libredragon.model.expressionapi.term.types.Expr  t) {
return  (t instanceof libredragon.model.expressionapi.term.types.expr.Infegal) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_make_Infegal( libredragon.model.expressionapi.term.types.Expr  t0,  libredragon.model.expressionapi.term.types.Expr  t1) { 
return  libredragon.model.expressionapi.term.types.expr.Infegal.make(t0, t1) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_Infegal_e1( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete1() ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_Infegal_e2( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete2() ;
}
private static boolean tom_is_fun_sym_Supegal( libredragon.model.expressionapi.term.types.Expr  t) {
return  (t instanceof libredragon.model.expressionapi.term.types.expr.Supegal) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_make_Supegal( libredragon.model.expressionapi.term.types.Expr  t0,  libredragon.model.expressionapi.term.types.Expr  t1) { 
return  libredragon.model.expressionapi.term.types.expr.Supegal.make(t0, t1) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_Supegal_e1( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete1() ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_Supegal_e2( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete2() ;
}
private static boolean tom_is_fun_sym_Divide( libredragon.model.expressionapi.term.types.Expr  t) {
return  (t instanceof libredragon.model.expressionapi.term.types.expr.Divide) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_make_Divide( libredragon.model.expressionapi.term.types.Expr  t0,  libredragon.model.expressionapi.term.types.Expr  t1) { 
return  libredragon.model.expressionapi.term.types.expr.Divide.make(t0, t1) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_Divide_e1( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete1() ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_Divide_e2( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete2() ;
}
private static boolean tom_is_fun_sym_Power( libredragon.model.expressionapi.term.types.Expr  t) {
return  (t instanceof libredragon.model.expressionapi.term.types.expr.Power) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_make_Power( libredragon.model.expressionapi.term.types.Expr  t0,  libredragon.model.expressionapi.term.types.Expr  t1) { 
return  libredragon.model.expressionapi.term.types.expr.Power.make(t0, t1) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_Power_e1( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete1() ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_slot_Power_e2( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.gete2() ;
}
private static boolean tom_is_fun_sym_Epsilon( libredragon.model.expressionapi.term.types.Expr  t) {
return  (t instanceof libredragon.model.expressionapi.term.types.expr.Epsilon) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_make_Epsilon() { 
return  libredragon.model.expressionapi.term.types.expr.Epsilon.make() ;
}
private static boolean tom_is_fun_sym_Nombre( libredragon.model.expressionapi.term.types.Expr  t) {
return  (t instanceof libredragon.model.expressionapi.term.types.expr.Nombre) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_make_Nombre( int  t0) { 
return  libredragon.model.expressionapi.term.types.expr.Nombre.make(t0) ;
}
private static  int  tom_get_slot_Nombre_i( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.geti() ;
}
private static boolean tom_is_fun_sym_Litteral( libredragon.model.expressionapi.term.types.Expr  t) {
return  (t instanceof libredragon.model.expressionapi.term.types.expr.Litteral) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_make_Litteral( String  t0) { 
return  libredragon.model.expressionapi.term.types.expr.Litteral.make(t0) ;
}
private static  String  tom_get_slot_Litteral_name( libredragon.model.expressionapi.term.types.Expr  t) {
return  t.getname() ;
}
private static boolean tom_is_fun_sym_ListPlus( libredragon.model.expressionapi.term.types.Expr  t) {
return  ((t instanceof libredragon.model.expressionapi.term.types.expr.ConsListPlus) || (t instanceof libredragon.model.expressionapi.term.types.expr.EmptyListPlus)) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_empty_list_ListPlus() { 
return  libredragon.model.expressionapi.term.types.expr.EmptyListPlus.make() ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_cons_list_ListPlus( libredragon.model.expressionapi.term.types.Expr  e,  libredragon.model.expressionapi.term.types.Expr  l) { 
return  libredragon.model.expressionapi.term.types.expr.ConsListPlus.make(e,l) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_head_ListPlus_Expr( libredragon.model.expressionapi.term.types.Expr  l) {
return  l.getHeadListPlus() ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_tail_ListPlus_Expr( libredragon.model.expressionapi.term.types.Expr  l) {
return  l.getTailListPlus() ;
}
private static boolean tom_is_empty_ListPlus_Expr( libredragon.model.expressionapi.term.types.Expr  l) {
return  l.isEmptyListPlus() ;
}

  private static   libredragon.model.expressionapi.term.types.Expr  tom_append_list_ListPlus( libredragon.model.expressionapi.term.types.Expr  l1,  libredragon.model.expressionapi.term.types.Expr  l2) {
    if( l1.isEmptyListPlus() ) {
      return l2;
    } else if( l2.isEmptyListPlus() ) {
      return l1;
    } else if( ((l1 instanceof libredragon.model.expressionapi.term.types.expr.ConsListPlus) || (l1 instanceof libredragon.model.expressionapi.term.types.expr.EmptyListPlus)) ) {
      if(  l1.getTailListPlus() .isEmptyListPlus() ) {
        return  libredragon.model.expressionapi.term.types.expr.ConsListPlus.make( l1.getHeadListPlus() ,l2) ;
      } else {
        return  libredragon.model.expressionapi.term.types.expr.ConsListPlus.make( l1.getHeadListPlus() ,tom_append_list_ListPlus( l1.getTailListPlus() ,l2)) ;
      }
    } else {
      return  libredragon.model.expressionapi.term.types.expr.ConsListPlus.make(l1,l2) ;
    }
  }
  private static   libredragon.model.expressionapi.term.types.Expr  tom_get_slice_ListPlus( libredragon.model.expressionapi.term.types.Expr  begin,  libredragon.model.expressionapi.term.types.Expr  end, libredragon.model.expressionapi.term.types.Expr  tail) {
    if( (begin==end) ) {
      return tail;
    } else if( (end==tail)  && ( end.isEmptyListPlus()  ||  (end==tom_empty_list_ListPlus()) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  libredragon.model.expressionapi.term.types.expr.ConsListPlus.make((( ((begin instanceof libredragon.model.expressionapi.term.types.expr.ConsListPlus) || (begin instanceof libredragon.model.expressionapi.term.types.expr.EmptyListPlus)) )? begin.getHeadListPlus() :begin),( libredragon.model.expressionapi.term.types.Expr )tom_get_slice_ListPlus((( ((begin instanceof libredragon.model.expressionapi.term.types.expr.ConsListPlus) || (begin instanceof libredragon.model.expressionapi.term.types.expr.EmptyListPlus)) )? begin.getTailListPlus() :tom_empty_list_ListPlus()),end,tail)) ;
  }
  private static boolean tom_is_fun_sym_ListMult( libredragon.model.expressionapi.term.types.Expr  t) {
return  ((t instanceof libredragon.model.expressionapi.term.types.expr.ConsListMult) || (t instanceof libredragon.model.expressionapi.term.types.expr.EmptyListMult)) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_empty_list_ListMult() { 
return  libredragon.model.expressionapi.term.types.expr.EmptyListMult.make() ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_cons_list_ListMult( libredragon.model.expressionapi.term.types.Expr  e,  libredragon.model.expressionapi.term.types.Expr  l) { 
return  libredragon.model.expressionapi.term.types.expr.ConsListMult.make(e,l) ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_head_ListMult_Expr( libredragon.model.expressionapi.term.types.Expr  l) {
return  l.getHeadListMult() ;
}
private static  libredragon.model.expressionapi.term.types.Expr  tom_get_tail_ListMult_Expr( libredragon.model.expressionapi.term.types.Expr  l) {
return  l.getTailListMult() ;
}
private static boolean tom_is_empty_ListMult_Expr( libredragon.model.expressionapi.term.types.Expr  l) {
return  l.isEmptyListMult() ;
}

  private static   libredragon.model.expressionapi.term.types.Expr  tom_append_list_ListMult( libredragon.model.expressionapi.term.types.Expr  l1,  libredragon.model.expressionapi.term.types.Expr  l2) {
    if( l1.isEmptyListMult() ) {
      return l2;
    } else if( l2.isEmptyListMult() ) {
      return l1;
    } else if( ((l1 instanceof libredragon.model.expressionapi.term.types.expr.ConsListMult) || (l1 instanceof libredragon.model.expressionapi.term.types.expr.EmptyListMult)) ) {
      if(  l1.getTailListMult() .isEmptyListMult() ) {
        return  libredragon.model.expressionapi.term.types.expr.ConsListMult.make( l1.getHeadListMult() ,l2) ;
      } else {
        return  libredragon.model.expressionapi.term.types.expr.ConsListMult.make( l1.getHeadListMult() ,tom_append_list_ListMult( l1.getTailListMult() ,l2)) ;
      }
    } else {
      return  libredragon.model.expressionapi.term.types.expr.ConsListMult.make(l1,l2) ;
    }
  }
  private static   libredragon.model.expressionapi.term.types.Expr  tom_get_slice_ListMult( libredragon.model.expressionapi.term.types.Expr  begin,  libredragon.model.expressionapi.term.types.Expr  end, libredragon.model.expressionapi.term.types.Expr  tail) {
    if( (begin==end) ) {
      return tail;
    } else if( (end==tail)  && ( end.isEmptyListMult()  ||  (end==tom_empty_list_ListMult()) )) {
      /* code to avoid a call to make, and thus to avoid looping during list-matching */
      return begin;
    }
    return  libredragon.model.expressionapi.term.types.expr.ConsListMult.make((( ((begin instanceof libredragon.model.expressionapi.term.types.expr.ConsListMult) || (begin instanceof libredragon.model.expressionapi.term.types.expr.EmptyListMult)) )? begin.getHeadListMult() :begin),( libredragon.model.expressionapi.term.types.Expr )tom_get_slice_ListMult((( ((begin instanceof libredragon.model.expressionapi.term.types.expr.ConsListMult) || (begin instanceof libredragon.model.expressionapi.term.types.expr.EmptyListMult)) )? begin.getTailListMult() :tom_empty_list_ListMult()),end,tail)) ;
  }
  

public static Expr binaryExpressionToExpr (Expression expression, String type, Expression first, Expression second) {
BinaryExpression bexpression = (BinaryExpression) expression;
if(type.compareTo("PLUS") == 0)
return 
tom_make_Plus(bexpression.firstExpression().expressionToExpr(),bexpression.secondExpression().expressionToExpr());
else if(type.compareTo("FOIS") == 0)
return 
tom_make_Mult(bexpression.firstExpression().expressionToExpr(),bexpression.secondExpression().expressionToExpr());
else if(type.compareTo("EGAL") == 0)
return 
tom_make_Eq(bexpression.firstExpression().expressionToExpr(),bexpression.secondExpression().expressionToExpr());
else if(type.compareTo("OR") == 0)
return 
tom_make_Or(bexpression.firstExpression().expressionToExpr(),bexpression.secondExpression().expressionToExpr());
else if(type.compareTo("AND") == 0)
return 
tom_make_And(bexpression.firstExpression().expressionToExpr(),bexpression.secondExpression().expressionToExpr());
else if(type.compareTo("DIFF") == 0)
return 
tom_make_Diff(bexpression.firstExpression().expressionToExpr(),bexpression.secondExpression().expressionToExpr());
else if(type.compareTo("INF") == 0)
return 
tom_make_Inf(bexpression.firstExpression().expressionToExpr(),bexpression.secondExpression().expressionToExpr());
else if(type.compareTo("SUP") == 0)
return 
tom_make_Sup(bexpression.firstExpression().expressionToExpr(),bexpression.secondExpression().expressionToExpr());
else if(type.compareTo("Infegal") == 0)
return 
tom_make_Infegal(bexpression.firstExpression().expressionToExpr(),bexpression.secondExpression().expressionToExpr());
else if(type.compareTo("Supegal") == 0)
return 
tom_make_Supegal(bexpression.firstExpression().expressionToExpr(),bexpression.secondExpression().expressionToExpr());
else if(type.compareTo("MOINS_B") == 0)
return 
tom_make_MoinsB(bexpression.firstExpression().expressionToExpr(),bexpression.secondExpression().expressionToExpr());
else if(type.compareTo("DIVIDE") == 0)
return 
tom_make_Divide(bexpression.firstExpression().expressionToExpr(),bexpression.secondExpression().expressionToExpr());
else if(type.compareTo("POWER") == 0)
return 
tom_make_Power(bexpression.firstExpression().expressionToExpr(),bexpression.secondExpression().expressionToExpr());
return 
tom_make_Epsilon();
}

public static Expr unaryExpressionToExpr(Expression expression, String type, Expression sub) {
if(type.compareTo("PARENTHESIS") == 0)
return 
tom_make_Parenthesis(sub.expressionToExpr());
else if(type.compareTo("NOT") == 0)
return 
tom_make_Non(sub.expressionToExpr());
else if(type.compareTo("SQRT") == 0)
return 
tom_make_Sqrt(sub.expressionToExpr());
else if(type.compareTo("FACTORIAL") == 0)
return 
tom_make_Factorial(sub.expressionToExpr());
else if(type.compareTo("MOINS_U") == 0)
return 
tom_make_MoinsU(sub.expressionToExpr());
return sub.expressionToExpr();
}

public static Expr primaryExpressionToExpr(Expression expression, String type, String name) {
if(type.compareTo("NOMBRE") == 0)
return 
tom_make_Nombre(Integer.valueOf(name));
return 
tom_make_Litteral(name);
}

public Expression exprToExpression(Expr e){

{
{
if (tom_is_sort_Expr(e)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )e))) {
if (tom_is_fun_sym_Plus((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
return new BinaryExpression("PLUS",exprToExpression(
tom_get_slot_Plus_e1((( libredragon.model.expressionapi.term.types.Expr )e))), exprToExpression(
tom_get_slot_Plus_e2((( libredragon.model.expressionapi.term.types.Expr )e)))); 
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )e))) {
if (tom_is_fun_sym_Mult((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
return new BinaryExpression("FOIS",exprToExpression(
tom_get_slot_Mult_e1((( libredragon.model.expressionapi.term.types.Expr )e))), exprToExpression(
tom_get_slot_Mult_e2((( libredragon.model.expressionapi.term.types.Expr )e)))); 
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )e))) {
if (tom_is_fun_sym_Eq((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
return new BinaryExpression("EGAL",exprToExpression(
tom_get_slot_Eq_e1((( libredragon.model.expressionapi.term.types.Expr )e))), exprToExpression(
tom_get_slot_Eq_e2((( libredragon.model.expressionapi.term.types.Expr )e)))); 
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )e))) {
if (tom_is_fun_sym_Parenthesis((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
return new UnaryExpression("PARENTHESIS", exprToExpression(
tom_get_slot_Parenthesis_e((( libredragon.model.expressionapi.term.types.Expr )e)))); 
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )e))) {
if (tom_is_fun_sym_Sqrt((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
return new UnaryExpression("SQRT", exprToExpression(
tom_get_slot_Sqrt_e((( libredragon.model.expressionapi.term.types.Expr )e)))); 
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )e))) {
if (tom_is_fun_sym_MoinsU((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
return new UnaryExpression("MOINS_U", exprToExpression(
tom_get_slot_MoinsU_e((( libredragon.model.expressionapi.term.types.Expr )e)))); 
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )e))) {
if (tom_is_fun_sym_Non((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
return new UnaryExpression("NOT", exprToExpression(
tom_get_slot_Non_e((( libredragon.model.expressionapi.term.types.Expr )e)))); 
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )e))) {
if (tom_is_fun_sym_Factorial((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
return new UnaryExpression("FACTORIAL", exprToExpression(
tom_get_slot_Factorial_e((( libredragon.model.expressionapi.term.types.Expr )e)))); 
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )e))) {
if (tom_is_fun_sym_MoinsB((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
return new BinaryExpression("MOINS_B",exprToExpression(
tom_get_slot_MoinsB_e1((( libredragon.model.expressionapi.term.types.Expr )e))), exprToExpression(
tom_get_slot_MoinsB_e2((( libredragon.model.expressionapi.term.types.Expr )e)))); 
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )e))) {
if (tom_is_fun_sym_Or((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
return new BinaryExpression("OR",exprToExpression(
tom_get_slot_Or_e1((( libredragon.model.expressionapi.term.types.Expr )e))), exprToExpression(
tom_get_slot_Or_e2((( libredragon.model.expressionapi.term.types.Expr )e)))); 
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )e))) {
if (tom_is_fun_sym_And((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
return new BinaryExpression("AND",exprToExpression(
tom_get_slot_And_e1((( libredragon.model.expressionapi.term.types.Expr )e))), exprToExpression(
tom_get_slot_And_e2((( libredragon.model.expressionapi.term.types.Expr )e)))); 
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )e))) {
if (tom_is_fun_sym_Diff((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
return new BinaryExpression("DIFF",exprToExpression(
tom_get_slot_Diff_e1((( libredragon.model.expressionapi.term.types.Expr )e))), exprToExpression(
tom_get_slot_Diff_e2((( libredragon.model.expressionapi.term.types.Expr )e)))); 
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )e))) {
if (tom_is_fun_sym_Inf((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
return new BinaryExpression("INF",exprToExpression(
tom_get_slot_Inf_e1((( libredragon.model.expressionapi.term.types.Expr )e))), exprToExpression(
tom_get_slot_Inf_e2((( libredragon.model.expressionapi.term.types.Expr )e)))); 
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )e))) {
if (tom_is_fun_sym_Sup((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
return new BinaryExpression("SUP",exprToExpression(
tom_get_slot_Sup_e1((( libredragon.model.expressionapi.term.types.Expr )e))), exprToExpression(
tom_get_slot_Sup_e2((( libredragon.model.expressionapi.term.types.Expr )e)))); 
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )e))) {
if (tom_is_fun_sym_Infegal((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
return new BinaryExpression("INFEGAL",exprToExpression(
tom_get_slot_Infegal_e1((( libredragon.model.expressionapi.term.types.Expr )e))), exprToExpression(
tom_get_slot_Infegal_e2((( libredragon.model.expressionapi.term.types.Expr )e)))); 
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )e))) {
if (tom_is_fun_sym_Supegal((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
return new BinaryExpression("SUPEGAL",exprToExpression(
tom_get_slot_Supegal_e1((( libredragon.model.expressionapi.term.types.Expr )e))), exprToExpression(
tom_get_slot_Supegal_e2((( libredragon.model.expressionapi.term.types.Expr )e)))); 
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )e))) {
if (tom_is_fun_sym_Divide((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
return new BinaryExpression("DIVIDE",exprToExpression(
tom_get_slot_Divide_e1((( libredragon.model.expressionapi.term.types.Expr )e))), exprToExpression(
tom_get_slot_Divide_e2((( libredragon.model.expressionapi.term.types.Expr )e)))); 
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )e))) {
if (tom_is_fun_sym_Power((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
return new BinaryExpression("POWER",exprToExpression(
tom_get_slot_Power_e1((( libredragon.model.expressionapi.term.types.Expr )e))), exprToExpression(
tom_get_slot_Power_e2((( libredragon.model.expressionapi.term.types.Expr )e)))); 
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )e))) {
if (tom_is_fun_sym_Nombre((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
return new PrimaryExpression("NOMBRE",""+
tom_get_slot_Nombre_i((( libredragon.model.expressionapi.term.types.Expr )e)));
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )e))) {
if (tom_is_fun_sym_Litteral((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
return new PrimaryExpression("LITTERAL",
tom_get_slot_Litteral_name((( libredragon.model.expressionapi.term.types.Expr )e)));
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )e))) {
if (tom_is_fun_sym_Epsilon((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
return null;
}
}
}
}
}

throw new RuntimeException("should not be there exprToExpression : " + e);
}

private static Expr generateListMult(Expr e){

{
{
if (tom_is_sort_Expr(e)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )e))) {
if (tom_is_fun_sym_Mult((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
return 
tom_cons_list_ListMult(generateListMult(tom_get_slot_Mult_e1((( libredragon.model.expressionapi.term.types.Expr )e))),tom_cons_list_ListMult(generateListMult(tom_get_slot_Mult_e2((( libredragon.model.expressionapi.term.types.Expr )e))) ,tom_empty_list_ListMult())); 
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
return 
(( libredragon.model.expressionapi.term.types.Expr )e); 
}
}
}

throw new RuntimeException("should not be there generateListMult : " + e);
}

private static Expr generateListPlus(Expr e){

{
{
if (tom_is_sort_Expr(e)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )e))) {
if (tom_is_fun_sym_Plus((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
return 
tom_cons_list_ListPlus(generateListPlus(tom_get_slot_Plus_e1((( libredragon.model.expressionapi.term.types.Expr )e))),tom_cons_list_ListPlus(generateListPlus(tom_get_slot_Plus_e2((( libredragon.model.expressionapi.term.types.Expr )e))) ,tom_empty_list_ListPlus())); 
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
return 
(( libredragon.model.expressionapi.term.types.Expr )e); 
}
}
}

throw new RuntimeException("should not be there generateListPlus: " + e);
}


private Expr listToOp(Expr e){

{
{
if (tom_is_sort_Expr(e)) {
if (tom_is_fun_sym_ListPlus((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
if ( ( tom_is_empty_ListPlus_Expr((( libredragon.model.expressionapi.term.types.Expr )e)) || tom_equal_term_Expr((( libredragon.model.expressionapi.term.types.Expr )e), tom_empty_list_ListPlus()) ) ) {
return 
tom_make_Epsilon(); 
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
if (tom_is_fun_sym_ListMult((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
if ( ( tom_is_empty_ListMult_Expr((( libredragon.model.expressionapi.term.types.Expr )e)) || tom_equal_term_Expr((( libredragon.model.expressionapi.term.types.Expr )e), tom_empty_list_ListMult()) ) ) {
return 
tom_make_Epsilon(); 
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
if (tom_is_fun_sym_ListPlus((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
if (!( ( tom_is_empty_ListPlus_Expr((( libredragon.model.expressionapi.term.types.Expr )e)) || tom_equal_term_Expr((( libredragon.model.expressionapi.term.types.Expr )e), tom_empty_list_ListPlus()) ) )) {
 libredragon.model.expressionapi.term.types.Expr  tomMatch4_6=((tom_is_fun_sym_ListPlus((( libredragon.model.expressionapi.term.types.Expr )e)))?(tom_get_tail_ListPlus_Expr((( libredragon.model.expressionapi.term.types.Expr )e))):(tom_empty_list_ListPlus()));
if (!( ( tom_is_empty_ListPlus_Expr(tomMatch4_6) || tom_equal_term_Expr(tomMatch4_6, tom_empty_list_ListPlus()) ) )) {
 libredragon.model.expressionapi.term.types.Expr  tomMatch4_7=((tom_is_fun_sym_ListPlus(tomMatch4_6))?(tom_get_tail_ListPlus_Expr(tomMatch4_6)):(tom_empty_list_ListPlus()));
if ( ( tom_is_empty_ListPlus_Expr(tomMatch4_7) || tom_equal_term_Expr(tomMatch4_7, tom_empty_list_ListPlus()) ) ) {
return 
tom_make_Plus(((tom_is_fun_sym_ListPlus((( libredragon.model.expressionapi.term.types.Expr )e)))?(tom_get_head_ListPlus_Expr((( libredragon.model.expressionapi.term.types.Expr )e))):((( libredragon.model.expressionapi.term.types.Expr )e))),((tom_is_fun_sym_ListPlus(tomMatch4_6))?(tom_get_head_ListPlus_Expr(tomMatch4_6)):(tomMatch4_6))); 
}
}
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
if (tom_is_fun_sym_ListPlus((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
if (!( ( tom_is_empty_ListPlus_Expr((( libredragon.model.expressionapi.term.types.Expr )e)) || tom_equal_term_Expr((( libredragon.model.expressionapi.term.types.Expr )e), tom_empty_list_ListPlus()) ) )) {
return 
tom_make_Plus(((tom_is_fun_sym_ListPlus((( libredragon.model.expressionapi.term.types.Expr )e)))?(tom_get_head_ListPlus_Expr((( libredragon.model.expressionapi.term.types.Expr )e))):((( libredragon.model.expressionapi.term.types.Expr )e))),listToOp(tom_append_list_ListPlus(((tom_is_fun_sym_ListPlus((( libredragon.model.expressionapi.term.types.Expr )e)))?(tom_get_tail_ListPlus_Expr((( libredragon.model.expressionapi.term.types.Expr )e))):(tom_empty_list_ListPlus())),tom_empty_list_ListPlus()))); 
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
if (tom_is_fun_sym_ListMult((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
if (!( ( tom_is_empty_ListMult_Expr((( libredragon.model.expressionapi.term.types.Expr )e)) || tom_equal_term_Expr((( libredragon.model.expressionapi.term.types.Expr )e), tom_empty_list_ListMult()) ) )) {
 libredragon.model.expressionapi.term.types.Expr  tomMatch4_14=((tom_is_fun_sym_ListMult((( libredragon.model.expressionapi.term.types.Expr )e)))?(tom_get_tail_ListMult_Expr((( libredragon.model.expressionapi.term.types.Expr )e))):(tom_empty_list_ListMult()));
if (!( ( tom_is_empty_ListMult_Expr(tomMatch4_14) || tom_equal_term_Expr(tomMatch4_14, tom_empty_list_ListMult()) ) )) {
 libredragon.model.expressionapi.term.types.Expr  tomMatch4_15=((tom_is_fun_sym_ListMult(tomMatch4_14))?(tom_get_tail_ListMult_Expr(tomMatch4_14)):(tom_empty_list_ListMult()));
if ( ( tom_is_empty_ListMult_Expr(tomMatch4_15) || tom_equal_term_Expr(tomMatch4_15, tom_empty_list_ListMult()) ) ) {
return 
tom_make_Mult(((tom_is_fun_sym_ListMult((( libredragon.model.expressionapi.term.types.Expr )e)))?(tom_get_head_ListMult_Expr((( libredragon.model.expressionapi.term.types.Expr )e))):((( libredragon.model.expressionapi.term.types.Expr )e))),((tom_is_fun_sym_ListMult(tomMatch4_14))?(tom_get_head_ListMult_Expr(tomMatch4_14)):(tomMatch4_14))); 
}
}
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
if (tom_is_fun_sym_ListMult((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )e)))) {
if (!( ( tom_is_empty_ListMult_Expr((( libredragon.model.expressionapi.term.types.Expr )e)) || tom_equal_term_Expr((( libredragon.model.expressionapi.term.types.Expr )e), tom_empty_list_ListMult()) ) )) {
return 
tom_make_Mult(((tom_is_fun_sym_ListMult((( libredragon.model.expressionapi.term.types.Expr )e)))?(tom_get_head_ListMult_Expr((( libredragon.model.expressionapi.term.types.Expr )e))):((( libredragon.model.expressionapi.term.types.Expr )e))),listToOp(tom_append_list_ListMult(((tom_is_fun_sym_ListMult((( libredragon.model.expressionapi.term.types.Expr )e)))?(tom_get_tail_ListMult_Expr((( libredragon.model.expressionapi.term.types.Expr )e))):(tom_empty_list_ListMult())),tom_empty_list_ListMult()))); 
}
}
}
}
{
if (tom_is_sort_Expr(e)) {
return 
(( libredragon.model.expressionapi.term.types.Expr )e);
}
}
}

throw new RuntimeException("should not be there listToOp : " + e);
}

/*private Expr associativite(Expr e1 Expr e2){
%match(e1, e2){
ListPlus(), _ -> { return `Epsilon();}
ListPlus
ListPlus(x,after*) -> { return `List (Plus(x, associativite(after*)), Plus(associativite(after*), x));}
ListPlus(x, y)-> { return `Plus(x, y);}
x -> { return `x; }
}
throw new RuntimeException("should not be there: " + e);
}*/


public static class Com extends tom.library.sl.AbstractStrategyBasic {
public Com() {
super(tom_make_Fail());
}
public tom.library.sl.Visitable[] getChildren() {
tom.library.sl.Visitable[] stratChildren = new tom.library.sl.Visitable[getChildCount()];
stratChildren[0] = super.getChildAt(0);
return stratChildren;}
public tom.library.sl.Visitable setChildren(tom.library.sl.Visitable[] children) {
super.setChildAt(0, children[0]);
return this;
}
public int getChildCount() {
return 1;
}
public tom.library.sl.Visitable getChildAt(int index) {
switch (index) {
case 0: return super.getChildAt(0);
default: throw new IndexOutOfBoundsException();
}
}
public tom.library.sl.Visitable setChildAt(int index, tom.library.sl.Visitable child) {
switch (index) {
case 0: return super.setChildAt(0, child);
default: throw new IndexOutOfBoundsException();
}
}
@SuppressWarnings("unchecked")
public <T> T visitLight(T v, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
if (tom_is_sort_Expr(v)) {
return ((T)visit_Expr((( libredragon.model.expressionapi.term.types.Expr )v),introspector));
}
if (!(( null  == environment))) {
return ((T)any.visit(environment,introspector));
} else {
return any.visitLight(v,introspector);
}
}
@SuppressWarnings("unchecked")
public  libredragon.model.expressionapi.term.types.Expr  _visit_Expr( libredragon.model.expressionapi.term.types.Expr  arg, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
if (!(( null  == environment))) {
return (( libredragon.model.expressionapi.term.types.Expr )any.visit(environment,introspector));
} else {
return any.visitLight(arg,introspector);
}
}
@SuppressWarnings("unchecked")
public  libredragon.model.expressionapi.term.types.Expr  visit_Expr( libredragon.model.expressionapi.term.types.Expr  tom__arg, tom.library.sl.Introspector introspector)
 throws tom.library.sl.VisitFailure {
{
{
if (tom_is_sort_Expr(tom__arg)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )tom__arg))) {
if (tom_is_fun_sym_Plus((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )tom__arg)))) {
return tom_make_Plus(tom_get_slot_Plus_e2((( libredragon.model.expressionapi.term.types.Expr )tom__arg)),tom_get_slot_Plus_e1((( libredragon.model.expressionapi.term.types.Expr )tom__arg)));
}
}
}
}
{
if (tom_is_sort_Expr(tom__arg)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )tom__arg))) {
if (tom_is_fun_sym_Mult((( libredragon.model.expressionapi.term.types.Expr )(( libredragon.model.expressionapi.term.types.Expr )tom__arg)))) {
return tom_make_Mult(tom_get_slot_Mult_e2((( libredragon.model.expressionapi.term.types.Expr )tom__arg)),tom_get_slot_Mult_e1((( libredragon.model.expressionapi.term.types.Expr )tom__arg)));
}
}
}
}
}
return _visit_Expr(tom__arg,introspector);
}
}
private static  tom.library.sl.Strategy  tom_make_Com() { 
return new Com();
}


public void eval (Expr e, ArrayList list){
Expr e1, e2, e3;

{
{
if (tom_is_sort_Expr(e)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )e))) {
 libredragon.model.expressionapi.term.types.Expr  tomMatch6_end_4=(( libredragon.model.expressionapi.term.types.Expr )e);
do {
{

e1 = listToOp(
tom_get_slice_ListPlus((( libredragon.model.expressionapi.term.types.Expr )e),tomMatch6_end_4,tom_empty_list_ListPlus()));
e2 = listToOp(
tomMatch6_end_4);
if( 
e1!= 
tom_make_Epsilon()&& 
e2!= 
tom_make_Epsilon()){
list.add(exprToExpression(
tom_make_Plus(e1,e2)));
try {
list.add(exprToExpression(
tom_make_Com().visit(
tom_make_Plus(e1,e2))));
} catch(VisitFailure f) {
lolo
System.out.println("the strategy failed");
}
}

if ( ( tom_is_empty_ListPlus_Expr(tomMatch6_end_4) || tom_equal_term_Expr(tomMatch6_end_4, tom_empty_list_ListPlus()) ) ) {
tomMatch6_end_4=(( libredragon.model.expressionapi.term.types.Expr )e);
} else {
tomMatch6_end_4=((tom_is_fun_sym_ListPlus(tomMatch6_end_4))?(tom_get_tail_ListPlus_Expr(tomMatch6_end_4)):(tom_empty_list_ListPlus()));
}
}
} while(!(tom_equal_term_Expr(tomMatch6_end_4, (( libredragon.model.expressionapi.term.types.Expr )e))));
}
}
}
{
if (tom_is_sort_Expr(e)) {
if (tom_is_sort_Expr((( libredragon.model.expressionapi.term.types.Expr )e))) {
 libredragon.model.expressionapi.term.types.Expr  tomMatch6_end_10=(( libredragon.model.expressionapi.term.types.Expr )e);
do {
{

e1 = listToOp(
tom_get_slice_ListMult((( libredragon.model.expressionapi.term.types.Expr )e),tomMatch6_end_10,tom_empty_list_ListMult()));
e2 = listToOp(
tomMatch6_end_10);
if( 
e1!= 
tom_make_Epsilon()&& 
e2!= 
tom_make_Epsilon()){
list.add(exprToExpression(
tom_make_Mult(e1,e2)));
try {
list.add(exprToExpression(
tom_make_Com().visit(
tom_make_Mult(e1,e2))));
} catch(VisitFailure f) {
System.out.println("the strategy failed");
}
}

if ( ( tom_is_empty_ListMult_Expr(tomMatch6_end_10) || tom_equal_term_Expr(tomMatch6_end_10, tom_empty_list_ListMult()) ) ) {
tomMatch6_end_10=(( libredragon.model.expressionapi.term.types.Expr )e);
} else {
tomMatch6_end_10=((tom_is_fun_sym_ListMult(tomMatch6_end_10))?(tom_get_tail_ListMult_Expr(tomMatch6_end_10)):(tom_empty_list_ListMult()));
}
}
} while(!(tom_equal_term_Expr(tomMatch6_end_10, (( libredragon.model.expressionapi.term.types.Expr )e))));
}
}
}
}

}


public ArrayList<Expression> run(Expression exp) {
ArrayList<Expression> list = new ArrayList();
Expr e = exp.expressionToExpr();
Expr plus = generateListPlus(e);
Expr fois = generateListMult(e);

eval(plus, list);
eval(fois, list);
return list;
}
}
