--Write a solution to find the second highest distinct salary from the Employee table. If there is no second highest salary, return null (return None in Pandas).
```sql
SELECT MAX(Salary) AS SecondHighestSalary
FROM Employee
WHERE Salary < (SELECT MAX(Salary) FROM Employee);
```


--Table: Employee
--
--+-------------+------+
--| Column Name | Type |
--+-------------+------+
--| id          | int  |
--| salary      | int  |
--+-------------+------+
--id is the primary key (column with unique values) for this table.
--Each row of this table contains information about the salary of an employee.
--
--
--Write a solution to find the nth highest salary from the Employee table. If there is no nth highest salary, return null.
--
--The result format is in the following example.

```sql
CREATE OR REPLACE FUNCTION getNthHighestSalary(n INT) RETURNS TABLE (Salary INT) AS $$
BEGIN
    RETURN QUERY
    SELECT Salary
    FROM (
        SELECT
            Salary,
            DENSE_RANK() OVER (ORDER BY Salary DESC) AS rank
        FROM Employee
    ) AS ranked_salaries
    WHERE rank = n;
END;
$$ LANGUAGE plpgsql;
```
