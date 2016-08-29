grammar Expression;
import Configuration;

root : sum EOF;
sum : product ( ADD product )*;
product : primitive ( MULT primitive )*;
primitive : NUMBER | VAR | '(' sum ')' | WS primitive | primitive WS | WS primitive WS ;
NUMBER : ([0-9]+ ('.' [0-9]*)? | '.' [0-9]+)(('e'|'E')('-'|'+')?[0-9]+)?;
VAR : [a-zA-Z]+;
WS : [ \t\r\n]+;
ADD: '+';
MULT: '*';
