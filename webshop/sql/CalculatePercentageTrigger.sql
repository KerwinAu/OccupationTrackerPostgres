-- Create the table if it does not exist
CREATE TABLE IF NOT EXISTS keauit00_occupied (
    id SERIAL PRIMARY KEY,
    count_checked_in_customer INT,
    day INT,
    day_type VARCHAR(255),
    local_date DATE,
    max_checkins_allowed INT,
    month VARCHAR(20),
    occupation_ratio DECIMAL(5, 2),
    time TIME,
    time_of_day VARCHAR(255)
);

-- Your other SQL statements here



CREATE OR REPLACE FUNCTION calculate_percentage_trigger_function()
RETURNS TRIGGER AS $$
BEGIN
    -- Update the OccupationRatio column with the calculated percentage
    UPDATE keauit00_Occupied
    SET occupation_ratio = (NEW.Count_Checked_In_customer::DECIMAL(5, 2) / NEW.Max_Checkins_allowed::DECIMAL(5, 2) * 100)
    WHERE keauit00_Occupied.id = NEW.id;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create the trigger
CREATE TRIGGER CalculatePercentageTrigger
AFTER INSERT
ON keauit00_Occupied
FOR EACH ROW
EXECUTE FUNCTION calculate_percentage_trigger_function();
