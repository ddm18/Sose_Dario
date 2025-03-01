<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${title}</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            padding: 0;
            background-color: #f5f5f5;
        }
        h1 {
            text-align: center;
        }
        .transportation {
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 15px;
            margin: 20px auto;
            background-color: #fff;
            max-width: 600px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .transportation.hidden {
            display: none;
        }
        .transportation.disabled {
            opacity: 0.5;
            pointer-events: none;
        }
        .transportation p {
            margin: 5px 0;
        }
        .no-results {
            font-weight: bold;
            color: red;
            text-align: center;
        }
        .button {
            margin-top: 10px;
            display: flex;
            justify-content: flex-end;
        }
        .button button {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 3px;
            padding: 10px 15px;
            cursor: pointer;
        }
        .button button:hover {
            background-color: #0056b3;
        }
        .back-button {
            display: block;
            margin: 20px auto;
            text-align: center;
        }
        .back-button button {
            background-color: #6c757d;
            color: #fff;
            border: none;
            border-radius: 3px;
            padding: 10px 15px;
            cursor: pointer;
        }
        .back-button button:hover {
            background-color: #5a6268;
        }
        .selected {
            font-weight: bold;
            color: green;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <h1>${title}</h1>

    <#if transportations?has_content>
        <#list transportations as transportation>
            <div class="transportation 
                <#if selectedTransportation?exists && 
                    selectedTransportation.serviceId == transportation.id && 
                    selectedTransportation.serviceType == transportation.service_type>
                    selected
                <#elseif selectedTransportation?exists>
                    hidden
                </#if>">
                <p><strong>Departure:</strong> ${transportation.departureName} 
                    (${transportation.departureLatitude}, ${transportation.departureLongitude})</p>
                <p><strong>Arrival:</strong> ${transportation.arrivalName} 
                    (${transportation.arrivalLatitude}, ${transportation.arrivalLongitude})</p>
                <p><strong>Service Type:</strong> ${transportation.service_type}</p>
                
                <#if transportation.service_type == "car" >
                <p><strong>Car Seller:</strong> ${transportation.carSeller}</p>
                
                <#elseif transportation.service_type == "flight" >
                <p><strong>Departure Time:</strong> ${transportation.departureDatetime}</p>
                <p><strong>Arrival Time:</strong> ${transportation.arrivalDatetime}</p>                
                </#if>
            
                <#if selectedTransportation?exists && 
                    selectedTransportation.serviceId == transportation.id && 
                    selectedTransportation.serviceType == transportation.service_type>
                    <p class="selected">This transportation is already selected.</p>
                <#else>
                    <form action="/hotels/transportation/store" method="post" class="button">
                        <input type="hidden" name="hotel1" value="${input_hotel_1}">
                        <input type="hidden" name="hotel2" value="${input_hotel_2}">
                        <input type="hidden" name="serviceType" value="${transportation.service_type}">
                        <input type="hidden" name="serviceId" value="${transportation.id}">
                        <#if transportation.service_type == "car" >
                        <input type="hidden" name="serviceId2" value="${transportation.id2}">
                        </#if>
                        <button type="submit">Save</button>
                    </form>
                </#if>
            </div>
        </#list>
    <#else>
        <p class="no-results">No transportations available.</p>
    </#if>

    <div class="back-button">
        <button onclick="window.history.back()">Go Back</button>
    </div>
</body>
</html>
