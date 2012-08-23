package net.notgandhi.invoice.domain.model;

import com.google.common.collect.ImmutableList;
import net.notgandhi.invoice.support.tax.Rule;
import net.notgandhi.invoice.support.tax.Tax;
import net.notgandhi.invoice.support.tax.TaxStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * TaxStrategy implementation that allows for complex logic in the application of taxes through the use of rules.
 */
public class RuleBasedTaxStrategy implements TaxStrategy {
    private List<Rule> rules;

    public RuleBasedTaxStrategy() {
        this(new ArrayList<Rule>());
    }

    public RuleBasedTaxStrategy(List<Rule> rules) {
        this.setRules(rules);
    }

    @Override
    public Float getAdjustment(Float price, Set<Tax> taxes, Set<Taxable> taxables) {
        return this.apply(price, taxes, taxables) - price;
    }

    @Override
    public Float apply(Float price, Set<Tax> taxes, Set<Taxable> taxables) {
        Float total = price;

        for (Tax tax : taxes) {
            for (Rule rule : this.rules()) {
                if (rule.applies(tax)) {
                    if(rule.isValid(taxables) == false) {
                        // if rule applies and isn't valid, we skip it.
                        continue;
                    }
                }
            }

            total+= tax.getBurden(price);
        }

        return total;
    }

    private List<Rule> rules() {
        return rules;
    }

    public ImmutableList<Rule> getRules() {
        return new ImmutableList.Builder<Rule>().addAll(this.rules()).build();
    }

    private void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public boolean addRule(Rule rule) {
        return this.rules().add(rule);
    }

    public boolean removeRule(Rule rule) {
        return this.rules().remove(rule);
    }
}
