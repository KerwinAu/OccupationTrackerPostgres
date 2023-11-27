-- Create TRIGGER UpdateOtherEntries
-- ON [dbo].[keauit00_occupied]
-- AFTER UPDATE
-- AS
-- BEGIN
--     -- Check if [max_checkins_allowed] was updated
--     IF UPDATE([max_checkins_allowed])
--     BEGIN
--         -- Get the updated [max_checkins_allowed] value
--         DECLARE @newMaxCheckinsAllowed INT;
--         SELECT @newMaxCheckinsAllowed = [max_checkins_allowed]
--         FROM inserted;

--         -- Update all entries
--         UPDATE [dbo].[keauit00_occupied]
--         SET
--             [max_checkins_allowed] = @newMaxCheckinsAllowed;
--     END
-- END;

CREATE OR REPLACE FUNCTION public.update_other_entries_function()
RETURNS TRIGGER AS $$
BEGIN
    -- Check if max_checkins_allowed was updated
    IF NEW.max_checkins_allowed IS DISTINCT FROM OLD.max_checkins_allowed THEN
        -- Update all entries
        UPDATE public.keauit00_occupied
        SET max_checkins_allowed = NEW.max_checkins_allowed;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_other_entries_trigger
AFTER UPDATE ON public.keauit00_occupied
FOR EACH ROW
EXECUTE FUNCTION public.update_other_entries_function();

