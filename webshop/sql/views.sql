-- HighestCheckedInCustomerView
CREATE or replace VIEW HighestCheckedInCustomerView AS
SELECT
    id,
    count_checked_in_customer,
    day,
    day_type,
    local_date,
    max_checkins_allowed,
    month,
    time,
    time_of_day,
    occupation_ratio
FROM keauit00_occupied
ORDER BY count_checked_in_customer DESC;


-- LowestCheckedInCustomerView
CREATE or replace VIEW LowestCheckedInCustomerView AS
SELECT
    id,
    count_checked_in_customer,
    day,
    day_type,
    local_date,
    max_checkins_allowed,
    month,
    time,
    time_of_day,
    occupation_ratio
FROM keauit00_occupied
ORDER BY count_checked_in_customer;


-- WeekdaysView
CREATE or replace VIEW WeekdaysView AS
SELECT
    id,
    count_checked_in_customer,
    day,
    day_type,
    local_date,
    max_checkins_allowed,
    month,
    time,
    time_of_day,
    occupation_ratio
FROM keauit00_occupied
WHERE day_type IN ('WEEKDAY');

-- WeekendDaysView
CREATE or replace VIEW WeekendDaysView AS
SELECT
    id,
    count_checked_in_customer,
    day,
    day_type,
    local_date,
    max_checkins_allowed,
    month,
    time,
    time_of_day,
    occupation_ratio
FROM keauit00_occupied
WHERE day_type IN ('WEEKEND');
