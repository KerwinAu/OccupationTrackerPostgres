-- -- �ndern Sie den vorhandenen AFTER INSERT Trigger
-- Create TRIGGER CalculatePercentageTrigger
-- ON keauit00_Occupied
-- AFTER INSERT
-- AS
-- BEGIN
--     -- Update the OccupationRatio column with the calculated percentage
--     UPDATE keauit00_Occupied
--     SET occupation_ratio = (CAST(i.Count_Checked_In_customer AS DECIMAL(5, 2)) / CAST(i.Max_Checkins_allowed AS DECIMAL(5, 2)) * 100)
--     FROM inserted i
--     WHERE keauit00_Occupied.id = i.id;
-- END;

-- Create a new trigger function
CREATE OR REPLACE FUNCTION public.calculate_percentage_function()
RETURNS TRIGGER AS $$
BEGIN
    -- Update the OccupationRatio column with the calculated percentage
    NEW.occupation_ratio := (NEW.count_checked_in_customer::DECIMAL(5, 2) / NEW.max_checkins_allowed::DECIMAL(5, 2)) * 100;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create a trigger on the keauit00_occupied table
CREATE TRIGGER calculate_percentage_trigger
BEFORE INSERT ON keauit00_occupied
FOR EACH ROW
EXECUTE FUNCTION public.calculate_percentage_function();
