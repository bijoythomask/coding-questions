--Question:
--In SQL, id is the primary key for this table.
--id is an autoincrement column starting from 1.
--Find all numbers that appear at least three times consecutively.
--Return the result table in any order.
--The result format is in the following example.
```sql
WITH ConsecutiveNumbers AS (
    SELECT
        id,
        num,
        LAG(num, 1) OVER (ORDER BY id) AS prev_num,
        LAG(num, 2) OVER (ORDER BY id) AS prev2_num
    FROM your_table
)
SELECT DISTINCT num
FROM ConsecutiveNumbers
WHERE num = prev_num AND num = prev2_num;


