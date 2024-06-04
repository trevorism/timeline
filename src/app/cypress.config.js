import { defineConfig } from "cypress";
import registerTrevorismEventSender from "@trevorism/cypress-test-result-events";

export default defineConfig({

  e2e: {
    setupNodeEvents(on, config) {
      registerTrevorismEventSender({on, config}, 'timeline');
    }
  },

  component: {
    devServer: {
      framework: "vue",
      bundler: "vite",
    },
  },
});
