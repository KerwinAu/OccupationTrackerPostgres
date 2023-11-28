CREATE OR REPLACE FUNCTION public.get_monthly_records(
    selected_month VARCHAR(20)
)
RETURNS TABLE (
    id INT,
    count_checked_in_customer INT,
    day INT,
    day_type VARCHAR(255),
    local_date DATE,
    max_checkins_allowed INT,
    month VARCHAR(20),
    occupation_ratio DECIMAL(5, 2),
    event_time TIME, -- Changed from 'time' to 'event_time'
    time_of_day VARCHAR(255)
)
AS $$
BEGIN
    -- Your SQL logic here
    RETURN QUERY
    SELECT *
    FROM keauit00_occupied
    WHERE month = selected_month
    ORDER BY local_date, event_time;
END;
$$ LANGUAGE plpgsql;
