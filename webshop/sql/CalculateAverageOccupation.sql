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
