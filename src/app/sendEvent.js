import fs from 'fs/promises';
import path, { dirname } from 'path';
import { fileURLToPath } from 'url';
import axios from 'axios';

async function afterCypressRun() {
    // Get the directory of the current module file
    const __dirname = dirname(fileURLToPath(import.meta.url));

    // Define the path to the results.json file
    const resultsPath = path.join(__dirname, 'results.json');

    try {
        // Read the file
        const data = await fs.readFile(resultsPath, 'utf8');
        // Parse JSON string to JSON object
        const results = JSON.parse(data);

        const testEvent = {
            service: 'timeline',
            kind: 'cypress',
            success: results.totals.failed === 0,
            numberOfTests: results.totals.tests,
            durationMillis: 0,
            date: new Date().toISOString(),
        }

        axios.post("https://event.data.trevorism.com/event/testResult", testEvent).then(
            (response) => {
                console.log("Successfully sent cypress test result event.");
            },
            (error) => {
                console.error(error);
            }
        );

        //delete file at resultsPath
        await fs.unlink(resultsPath);

    } catch (err) {
        console.error(`Error reading file from disk: ${err}`);
    }
}

afterCypressRun();