package analizadores;
import java_cup.runtime.Symbol; 

%% 
%class Lexico
%public 
%line 
%char 
%cup 
%unicode
%ignorecase

%init{ 
    yyline = 1; 
    yychar = 1; 
%init} 



BLANCOS=[ \s\t\r\n\f]+
S=[ \!\"\#\$\%\&\'\(\)\*\+\,\-\.\/\:\;\<\=\>\?\@\[\]\^\_\{\|\}]
SE=("\\""n" | "\\""\'" | "\\""\"" )
SS="!"|"\""|"#"|"$"|"%"|"&"|"\'"|"("|")"|"*"|"+"|","|"-"|"."|"/"|":"|";"|"<"|"="|">"|"?"|"@"|"["|"]"|"^"|"_"|"{"|"|"|"}";
L=[a-zA-Z]
D=[0-9]*
DD=[0-9]+("."[ |0-9]+)?


LLAVE_A="{"
LLAVE_C="}"
PUNTO_COMA=";"
CONCA="."
MAS="+"
OR="|"
GUION="-"
INTERROGACION="?"
ASTERISCO="*"
FLECHA=">"

//conjuntos
RESERVADA="CONJ"
DOS_PUNTOS=":"
CONJUNTO=(({L}"~"{L})|({D}"~"{D})|({S}"~"{S})|({L}+(","{L})+)*|({D}+(","{D})+)*|({S}(","{S})+)*)
DELIMITADOR="%%"


CM=("//".*\n)|("//".*\r)
CM2=("<" "!"[^\!]* "!"">")

ECOM_EXP=([\"]{S}[\"]|[\"]{L}[\"]|[\"]{D}*[\"]|[\"][\"])
ESP_CONJER={SE}

NOMBRES={L}({L}|"_"|{D})*
ID_CONJER="{" [a-zA-Z0-9_]+ "}"
LEXEMA=[\"]({L}|{SS}|{D})*[\"]


%{

%}

%%

{BLANCOS} {}
{CM} {}
{CM2} {}

{OR} {return new Symbol(sym.OR,yyline,yychar, yytext());}
{MAS} {return new Symbol(sym.MAS,yyline,yychar, yytext());}
{CONCA} {return new Symbol(sym.CONCA,yyline,yychar, yytext());}
{ECOM_EXP} {return new Symbol(sym.ECOM_EXP,yyline,yychar, yytext());}
{PUNTO_COMA} {return new Symbol(sym.PUNTO_COMA,yyline,yychar, yytext());}
{GUION} {return new Symbol(sym.GUION,yyline,yychar, yytext());}
{FLECHA} {return new Symbol(sym.FLECHA,yyline,yychar, yytext());}
{ASTERISCO} {return new Symbol(sym.ASTERISCO,yyline,yychar, yytext());}
{ESP_CONJER} {return new Symbol(sym.ESP_CONJER,yyline,yychar, yytext());}
{INTERROGACION} {return new Symbol(sym.INTERROGACION,yyline,yychar, yytext());}
{LLAVE_A} {return new Symbol(sym.LLAVE_A,yyline,yychar, yytext());}
{RESERVADA} {return new Symbol(sym.RESERVADA,yyline,yychar, yytext());}
{DOS_PUNTOS} {return new Symbol(sym.DOS_PUNTOS,yyline,yychar, yytext());}
{CONJUNTO} {return new Symbol(sym.CONJUNTO,yyline,yychar, yytext());}
{DELIMITADOR} {return new Symbol(sym.DELIMITADOR,yyline,yychar, yytext());}
{NOMBRES} {return new Symbol(sym.NOMBRES,yyline,yychar, yytext());}
{LEXEMA} {return new Symbol(sym.LEXEMA,yyline,yychar, yytext());}
{ID_CONJER} {return new Symbol(sym.ID_CONJER,yyline,yychar, yytext());}


{LLAVE_C} {return new Symbol(sym.LLAVE_C,yyline,yychar, yytext());}

. {
    System.out.println("Este es un error lexico: "+yytext()+
    ", en la linea: "+yyline+", en la columna: "+yychar);
}










