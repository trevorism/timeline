describe('creating a single work history and clicking the generate timeline', () => {
    it('the timeline component renders, with a full bar', () => {
        cy.visit('https://timeline.draw.trevorism.com')
        cy.contains("Add Row").click()
        cy.get('input').eq(0).type('Test Company')
        cy.get('input').eq(1).type('01/01/2022')
        cy.get('input').eq(2).type('01/01/2023')
        cy.contains("Generate Timeline").click()

        expect(cy.get("svg").should('exist'))
        expect(cy.contains("Hide").should('exist'))
        expect(cy.contains("Convert To PNG").should('exist'))
    })
})