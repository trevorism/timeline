import { defineConfig } from "cypress";
import registerCypressJsonResults from "cypress-json-results";

let allResults = {};

export default defineConfig({

  e2e: {
    setupNodeEvents(on, config) {
      registerCypressJsonResults({
        on, filename: 'results.json'
      });
    }
  },

  component: {
    devServer: {
      framework: "vue",
      bundler: "vite",
    },
  },
});
