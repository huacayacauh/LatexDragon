package libreDragon.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RulesConfiguration {
	private Map< String, List<Rule> > rules;
	
	public RulesConfiguration() {
		rules = new HashMap< String, List<Rule>>();
	}
	
	public void addRule(String input_type, Rule rule) {
		if( rules.containsKey(input_type) )
			rules.get(input_type).add(rule);
		else {
			rules.put(input_type, new LinkedList<Rule>());
			rules.get(input_type).add(rule);
		}
	}
	
	public void addRules(String input_type, List<Rule> rules) {
		for(Rule rule : rules)
			addRule(input_type, rule);
	}
	
	public List<Rule> getRuleList(String input_type) {
		if( rules.containsKey(input_type) )
			return rules.get(input_type);
		else return new LinkedList<Rule>();
	}

	public Map< String, List<Rule> > getRules() {
		return rules;
	}
}
