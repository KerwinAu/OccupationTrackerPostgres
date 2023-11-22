-- create PROCEDURE [dbo].[GetMonthlyRecords]
--     @selectedMonth NVARCHAR(20)
-- AS
-- BEGIN
--     SET NOCOUNT ON;

--     SELECT *
--     FROM keauit00_occupied
--     WHERE [month] = @selectedMonth
--     ORDER BY [local_date], [time];
-- END;

CREATE OR REPLACE FUNCTION public.GetMonthlyRecords(
    selectedMonth VARCHAR(20)
)
RETURNS SETOF keauit00_occupied AS
$$
BEGIN
    RETURN QUERY
    SELECT *
    FROM keauit00_occupied
    WHERE "month" = selectedMonth
    ORDER BY local_date, time;
END;
$$ LANGUAGE plpgsql;

-- SELECT * FROM public.GetMonthlyRecords('NOVEMBER');

