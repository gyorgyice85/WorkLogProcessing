# Work Log Processing
 The app groups the work log based on the dates it contains. Each day's entries are saved in a separate file.

## Usage
```bash
java Main WorkLog.csv
```

Example WorkLog.csv

----------------------------
|Date | Time  Spent | Work Description      | 
|-----|-------------|-----------------------| 
| 2024-02-01 | 3.5h | koordination,analysis | 
| 2024-02-02 | 3h | workshop              |  
| 2024-02-02 | 1.5h | negotiation           |  
| 2024-03-24 | 1h | testing               |


 **It creates the following files:**

 *20240201*

|Date | Time Spent | Work Description |
|-----|------------|------------------|
| 2024-02-01 | 3.5h | koordination,analysis | 


 *20240202*

| Date       | Time Spent | Work Description |
|------------|----------|------------------|
| 2024-02-02 | 3h | workshop| 
| 2024-02-02 | 1.5h | negotiation           |  

 *20240224*

| Date       | Time Spent | Work Description |
|------------|----------|------------------|
| 2024-03-24 | 1h | testing               |
