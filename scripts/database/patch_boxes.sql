update stock set box_number = box_index+0  , box_index=box_number+0 from strain s , host_origin ho, species sp where strain_id=s.id and s.host_origin_id=ho.id and ho.species_id=sp.id and sp.common_name='Stickleback' ;




--select 
--st.box_number,st.box_index
--from strain s, stock st , host_origin ho, species sp
--where 
--st.strain_id=s.id
--and 
--s.host_origin_id=ho.id
--and 
--ho.species_id=sp.id
--and
--sp.common_name='Stickleback'
--order by st.box_number asc, st.box_index asc
