<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Flight and Airport Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        fieldset {
            margin-bottom: 20px;
            padding: 10px;
            width: 400px;
        }
        legend {
            font-size: 1.2em;
            font-weight: bold;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"] {
            width: 100%;
            padding: 5px;
            margin-top: 3px;
            margin-bottom: 10px;
            box-sizing: border-box;
        }
        button {
            padding: 8px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            margin-bottom: 10px; /* Add margin below buttons */
        }
        button:hover {
            background-color: #45a049;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h2>Flight and Airport Management</h2>
    
    <!-- Buttons for listing flights and airports -->
    <button onclick="window.location.href='/flights'">List All Flights</button>
    <button onclick="window.location.href='/airports'">List All Airports</button>
    
    

</body>
</html>