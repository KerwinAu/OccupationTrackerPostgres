-- CREATE PROCEDURE [dbo].[GetRecordsByDate]
--     @selectedDate DATE
-- AS
-- BEGIN
--     SET NOCOUNT ON;

--     SELECT *
--     FROM keauit00_occupied
--     WHERE [local_date] = @selectedDate
--     ORDER BY [time];
-- END;

CREATE OR REPLACE FUNCTION public.GetRecordsByDate(
    selectedDate DATE
)
RETURNS SETOF keauit00_occupied AS
$$
BEGIN
    RETURN QUERY
    SELECT *
    FROM keauit00_occupied
    WHERE local_date = selectedDate
    ORDER BY time;
END;
$$ LANGUAGE plpgsql;

-- SELECT * FROM GetRecordsByDate('22-11-2023'::DATE);