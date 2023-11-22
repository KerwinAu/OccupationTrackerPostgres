
-- ALTER FUNCTION CalculateAverageOccupation
-- (
--     @selectedDay NVARCHAR(10) = NULL,
--     @selectedTimeOfDay NVARCHAR(10) = NULL
-- )
-- RETURNS TABLE
-- AS
-- RETURN (
--     SELECT
--         CASE
--             WHEN DATEPART(WEEKDAY, local_Date) = 1 THEN 'Monday'
--             WHEN DATEPART(WEEKDAY, local_Date) = 2 THEN 'Tuesday'
--             WHEN DATEPART(WEEKDAY, local_Date) = 3 THEN 'Wednesday'
--             WHEN DATEPART(WEEKDAY, local_Date) = 4 THEN 'Thursday'
--             WHEN DATEPART(WEEKDAY, local_Date) = 5 THEN 'Friday'
--             WHEN DATEPART(WEEKDAY, local_Date) = 6 THEN 'Saturday'
--             WHEN DATEPART(WEEKDAY, local_Date) = 7 THEN 'Sunday'
--         END AS DayOfWeek,
--         time_of_day AS TimeOfDay,
--         COUNT(*) AS DayCount ,
--         Cast (AVG(count_checked_in_customer)as bigint) AS Avg_CountCheckedInCustomer,
--         CAST(AVG(OccupationRatio)as bigint) AS Avg_OccupationRatio
--     FROM keauit00_Occupied
--     WHERE
--         (@selectedDay IS NULL OR 
--             (
--                 (@selectedDay = 'Monday' AND DATEPART(WEEKDAY, local_Date) = 1) OR
--                 (@selectedDay = 'Tuesday' AND DATEPART(WEEKDAY, local_Date) = 2) OR
--                 (@selectedDay = 'Wednesday' AND DATEPART(WEEKDAY, local_Date) = 3) OR
--                 (@selectedDay = 'Thursday' AND DATEPART(WEEKDAY, local_Date) = 4) OR
--                 (@selectedDay = 'Friday' AND DATEPART(WEEKDAY, local_Date) = 5) OR
--                 (@selectedDay = 'Saturday' AND DATEPART(WEEKDAY, local_Date) = 6) OR
--                 (@selectedDay = 'Sunday' AND DATEPART(WEEKDAY, local_Date) = 7)
--             )
--         )
--     AND (@selectedTimeOfDay IS NULL OR time_of_day = @selectedTimeOfDay)
--     GROUP BY
--         CASE
--             WHEN DATEPART(WEEKDAY, local_Date) = 1 THEN 'Monday'
--             WHEN DATEPART(WEEKDAY, local_Date) = 2 THEN 'Tuesday'
--             WHEN DATEPART(WEEKDAY, local_Date) = 3 THEN 'Wednesday'
--             WHEN DATEPART(WEEKDAY, local_Date) = 4 THEN 'Thursday'
--             WHEN DATEPART(WEEKDAY, local_Date) = 5 THEN 'Friday'
--             WHEN DATEPART(WEEKDAY, local_Date) = 6 THEN 'Saturday'
--             WHEN DATEPART(WEEKDAY, local_Date) = 7 THEN 'Sunday'
--         END, 
--         time_of_day
-- );


CREATE OR REPLACE FUNCTION public.CalculateAverageOccupation(
    selectedDay VARCHAR(10) DEFAULT NULL,
    selectedTimeOfDay VARCHAR(10) DEFAULT NULL
)
RETURNS TABLE (
    DayOfWeek TEXT,
    TimeOfDay VARCHAR(10),
    DayCount BIGINT, -- Change from INT to BIGINT
    Avg_CountCheckedInCustomer BIGINT,
    Avg_OccupationRatio BIGINT
)
AS $$
BEGIN
    RETURN QUERY
    SELECT
        CASE
            WHEN EXTRACT(DOW FROM local_date) = 1 THEN 'Monday'
            WHEN EXTRACT(DOW FROM local_date) = 2 THEN 'Tuesday'
            WHEN EXTRACT(DOW FROM local_date) = 3 THEN 'Wednesday'
            WHEN EXTRACT(DOW FROM local_date) = 4 THEN 'Thursday'
            WHEN EXTRACT(DOW FROM local_date) = 5 THEN 'Friday'
            WHEN EXTRACT(DOW FROM local_date) = 6 THEN 'Saturday'
            WHEN EXTRACT(DOW FROM local_date) = 7 THEN 'Sunday'
        END AS DayOfWeek,
        time_of_day AS TimeOfDay,
        COUNT(*) AS DayCount,
        CAST(AVG(count_checked_in_customer) AS BIGINT) AS Avg_CountCheckedInCustomer,
        CAST(AVG(Occupation_Ratio) AS BIGINT) AS Avg_OccupationRatio
    FROM keauit00_Occupied
    WHERE
        (selectedDay IS NULL OR 
            (
                (selectedDay = 'Monday' AND EXTRACT(DOW FROM local_date) = 1) OR
                (selectedDay = 'Tuesday' AND EXTRACT(DOW FROM local_date) = 2) OR
                (selectedDay = 'Wednesday' AND EXTRACT(DOW FROM local_date) = 3) OR
                (selectedDay = 'Thursday' AND EXTRACT(DOW FROM local_date) = 4) OR
                (selectedDay = 'Friday' AND EXTRACT(DOW FROM local_date) = 5) OR
                (selectedDay = 'Saturday' AND EXTRACT(DOW FROM local_date) = 6) OR
                (selectedDay = 'Sunday' AND EXTRACT(DOW FROM local_date) = 7)
            )
        )
    AND (selectedTimeOfDay IS NULL OR time_of_day = selectedTimeOfDay)
    GROUP BY DayOfWeek, time_of_day;
END;
$$ LANGUAGE plpgsql;


-- SELECT * FROM public.CalculateAverageOccupation('Tuesday', 'afternoon');