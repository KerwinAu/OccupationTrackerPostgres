-- FUNCTION: public.getmonthlyrecords(character varying)

-- DROP FUNCTION IF EXISTS public.getmonthlyrecords(character varying);

CREATE OR REPLACE FUNCTION public.getmonthlyrecords(
	selectedmonth character varying)
    RETURNS SETOF keauit00_occupied 
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
    ROWS 1000

AS $BODY$
BEGIN
    RETURN QUERY
    SELECT *
    FROM keauit00_occupied
    WHERE "month" = selectedMonth
    ORDER BY local_date, time;
END;
$BODY$;

ALTER FUNCTION public.getmonthlyrecords(character varying)
    OWNER TO postgres;
