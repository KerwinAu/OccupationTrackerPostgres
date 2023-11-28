CREATE OR REPLACE FUNCTION public.get_records_by_date(
    selected_date DATE
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
    WHERE local_date = selected_date
    ORDER BY event_time;
END;
$$ LANGUAGE plpgsql;
