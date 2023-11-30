-- Create the table if it does not exist
CREATE TABLE IF NOT EXISTS keauit00_occupied (
    id SERIAL PRIMARY KEY,
    count_checked_in_customer INT,
    day VARCHAR(20),
    day_type VARCHAR(255),
    local_date DATE,
    max_checkins_allowed INT,
    month VARCHAR(20),
    occupation_ratio DECIMAL(5, 2),
    time TIME,
    time_of_day VARCHAR(255)
);

INSERT INTO keauit00_occupied (
    count_checked_in_customer,
    day,
    day_type,
    local_date,
    max_checkins_allowed,
    month,
    occupation_ratio,
    time,
    time_of_day
) VALUES
(24,2, 'WEEKDAY', '2023-10-10', 123, 'OCTOBER', 100, '19:43:30.0000000', 'evening'),
(33,3, 'WEEKDAY', '2023-10-11', 123, 'OCTOBER', 100, '20:13:14.0000000', 'evening'),
(31,4, 'WEEKDAY', '2023-10-12', 123, 'OCTOBER', 100, '20:43:14.0000000', 'evening'),
(56, 5, 'WEEKDAY', '2023-10-13', 123, 'OCTOBER', 100, '21:08:48.0000000', 'evening'),
(38, 6, 'WEEKDAY', '2023-10-14', 123, 'OCTOBER', 30.894308, '21:38:48.0000000', 'evening'),
(23, 7, 'WEEKDAY', '2023-10-15', 123, 'OCTOBER', 18.699186, '22:08:48.0000000', 'evening'),
(17, 1, 'WEEKDAY', '2023-10-16', 123, 'OCTOBER', 13.821138, '22:38:48.0000000', 'evening'),
(20,  2, 'WEEKDAY', '2023-10-17', 123, 'OCTOBER', 16.260162, '22:56:32.0000000', 'evening'),
(20,  3, 'WEEKDAY', '2023-11-10', 123, 'NOVEMBER', 16.260162, '22:56:43.0000000', 'evening'),
(20,  4, 'WEEKDAY', '2023-11-11', 123, 'NOVEMBER', 16.260162, '22:56:48.0000000', 'evening'),
(20,  5, 'WEEKDAY', '2023-11-12', 123, 'NOVEMBER', 16.260162, '22:56:56.0000000', 'evening'),
(20, 6, 'WEEKDAY', '2023-11-13', 123, 'NOVEMBER', 16.260162, '22:56:59.0000000', 'evening'),
(21,  7, 'WEEKDAY', '2023-11-14', 123, 'NOVEMBER', 17.07317, '22:57:04.0000000', 'evening'),
(21,  3, 'WEEKDAY', '2023-11-15', 123, 'NOVEMBER', 17.07317, '22:57:12.0000000', 'evening'),
(21,  5, 'WEEKDAY', '2023-11-16', 123, 'NOVEMBER', 17.07317, '22:57:26.0000000', 'evening'),
(19,  5, 'WEEKDAY', '2023-11-17', 123, 'NOVEMBER', 15.447154, '23:08:13.0000000', 'evening'),
(19,  4, 'WEEKDAY', '2023-11-18', 123, 'NOVEMBER', 15.447154, '23:11:29.0000000', 'evening'),
(19,  5, 'WEEKDAY', '2023-11-19', 123, 'NOVEMBER', 15.447154, '23:11:33.0000000', 'evening'),
(16, 1, 'WEEKDAY', '2023-11-20', 123, 'NOVEMBER', 13.00813, '23:13:37.0000000', 'evening');




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
CREATE or REPLACE TRIGGER CalculatePercentageTrigger
AFTER INSERT
ON keauit00_Occupied
FOR EACH ROW
EXECUTE FUNCTION calculate_percentage_trigger_function();
