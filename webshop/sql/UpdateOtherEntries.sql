-- Create or replace the function
CREATE OR REPLACE FUNCTION update_other_entries_function()
RETURNS TRIGGER AS $$
BEGIN
    -- Check if max_checkins_allowed was updated
    IF NEW.max_checkins_allowed IS DISTINCT FROM OLD.max_checkins_allowed THEN
        -- Update all entries with the new max_checkins_allowed value
        UPDATE keauit00_occupied
        SET max_checkins_allowed = NEW.max_checkins_allowed
        WHERE id = NEW.id;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create the trigger if it does not exist
CREATE or replace TRIGGER update_other_entries_trigger
AFTER UPDATE
ON keauit00_occupied
FOR EACH ROW
EXECUTE FUNCTION update_other_entries_function();
