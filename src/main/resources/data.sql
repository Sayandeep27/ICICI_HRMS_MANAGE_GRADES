🔥 1. MASTER DATA (MANDATORY)
✅ Insert Grade
INSERT INTO grades (grade_name, active)
VALUES ('G5', 1);
✅ Insert Currency
INSERT INTO currencies (code, name)
VALUES ('INR', 'Indian Rupee');
🔥 2. BASIC CHECK QUERIES
✅ Check all grade bands
SELECT * FROM grade_band;
✅ Check by grade
SELECT *
FROM grade_band
WHERE grade_id = 1;
✅ Check active records
SELECT *
FROM grade_band
WHERE effective_end_date IS NULL;
🔥 3. VERSIONING CHECK (MOST IMPORTANT)
SELECT 
    id,
    grade_id,
    version,
    effective_start_date,
    effective_end_date,
    status
FROM grade_band
WHERE grade_id = 1
ORDER BY version;
🔥 4. VALIDATION QUERIES
❌ Check multiple active records (SHOULD BE 1 ONLY)
SELECT grade_id, COUNT(*) AS active_count
FROM grade_band
WHERE effective_end_date IS NULL
GROUP BY grade_id
HAVING COUNT(*) > 1;
❌ Check invalid date logic (end < start)
SELECT *
FROM grade_band
WHERE effective_end_date < effective_start_date;
❌ Check version gaps
SELECT *
FROM grade_band
ORDER BY version;

👉 Manually verify sequence (1 → 2 → 3)

🔥 5. HISTORY TABLE QUERIES
✅ Full history
SELECT *
FROM change_history
ORDER BY change_date DESC;
✅ History for specific grade band
SELECT *
FROM change_history
WHERE grade_band_id = 10026
ORDER BY change_date DESC;
✅ Join with grade band
SELECT 
    ch.id,
    ch.action,
    ch.change_date,
    ch.changed_by,
    gb.grade_band_name,
    gb.version
FROM change_history ch
JOIN grade_band gb ON ch.grade_band_id = gb.id
ORDER BY ch.change_date DESC;
🔥 6. SEARCH SUPPORT QUERIES
🔹 By name contains
SELECT *
FROM grade_band
WHERE grade_band_name LIKE '%Band%';
🔹 By status
SELECT *
FROM grade_band
WHERE status = 'ACTIVE';
🔹 By grade
SELECT *
FROM grade_band
WHERE grade_id = 1;
🔥 7. CLEANUP QUERIES
❌ Delete all data (RESET)
DELETE FROM change_history;
DELETE FROM grade_band;
✅ Fix NULL version
UPDATE grade_band
SET version = 1
WHERE version IS NULL;
✅ Fix NULL changedBy
UPDATE change_history
SET changed_by = 'SYSTEM'
WHERE changed_by IS NULL;
🔥 8. PRODUCTION SAFETY QUERIES
✅ Ensure single active record
SELECT *
FROM grade_band
WHERE effective_end_date IS NULL;

👉 Should return only 1 row per grade

✅ Latest version per grade
SELECT *
FROM grade_band gb
WHERE version = (
    SELECT MAX(version)
    FROM grade_band
    WHERE grade_id = gb.grade_id
);
🔥 9. DEBUG QUERIES
🔍 Check overlapping dates
SELECT a.id, b.id
FROM grade_band a
JOIN grade_band b 
ON a.grade_id = b.grade_id 
AND a.id <> b.id
AND a.effective_start_date <= b.effective_end_date
AND b.effective_start_date <= a.effective_end_date;
🔍 Check timeline continuity
SELECT *
FROM grade_band
ORDER BY effective_start_date;