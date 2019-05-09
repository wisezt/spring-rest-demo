package com.luv2code.springdemo.rest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;



public class Test001{

    @Autowired
    protected ApplicationContext mApplicationContext;
    @Test
    public void expressionTemplatingTest() {
        /*
         * The bean factory resolver is needed to resolve the systemProperties
         * bean in the example expression.
         */
        final BeanFactoryResolver theBeanFactoryResolver =
                new BeanFactoryResolver(mApplicationContext);
        /* A template parser context is required with expression templating. */
        final TemplateParserContext theParserContext = new TemplateParserContext();
        final SpelExpressionParser theExpressionParser = new SpelExpressionParser();
        final Expression theExpression = theExpressionParser.parseExpression(
                "This computer uses the #{@systemProperties['os.name']} operating system.",
                theParserContext);
        final StandardEvaluationContext theEvaluationContext =
                new StandardEvaluationContext();
        theEvaluationContext.setBeanResolver(theBeanFactoryResolver);
        final Object theExpressionValue = theExpression.getValue(theEvaluationContext);
        System.out.println("Value: " + theExpressionValue);
        System.out.println("Value class: " + theExpressionValue.getClass().getName());
    }

}