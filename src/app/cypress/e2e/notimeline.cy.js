describe('clicking the generate timeline button first', () => {
    it('the timeline component renders, with no timeline', () => {
        cy.visit('https://timeline.draw.trevorism.com')
        cy.contains("Generate Timeline").click()

        expect(cy.contains("Hide").should('exist'))
        expect(cy.contains("Convert To PNG").should('exist'))
    })
})