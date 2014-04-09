update strain set notes = substring(notes from 5) where notes like 'null%' ;
